package me.leduyhung.mdaily.module.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.leduyhung.loglibrary.Logg;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import me.leduyhung.mdaily.helper.CacheUtil;
import me.leduyhung.mdaily.helper.GsonUtil;
import me.leduyhung.mdaily.observer.ObserverTag;
import me.leduyhung.mdaily.observer.UiObserver;

/**
 * Created by hungleduy on 11/3/17.
 */

public class User implements Serializable {

    public transient final String CACHE_NAME_USER = "CACHE_NAME_USER";

    private transient static User user;
    private transient CacheUtil cacheUtil;
    private transient CallbackManager callbackManager;

    private String id;
    private String name;
    private String avatar;

    public static User newInstance(Context context) {

        if (user == null) {

            user = new User(context);
        }

        return user;
    }

    public User(Context context) {

        cacheUtil = new CacheUtil(context);
        if (isLogin())
            user = GsonUtil.getGson().fromJson((cacheUtil.getString(CACHE_NAME_USER, "")), User.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isLogin() {

        return (cacheUtil.getString(CACHE_NAME_USER, "") != "");
    }

    public void login(Context mContext) {

        if (mContext instanceof Activity) {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().logInWithReadPermissions((Activity) mContext, Arrays.asList("user_photos", "email",
                    "user_birthday", "public_profile"));

            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {

                    Logg.error(User.this.getClass(), "login success-> " + loginResult.getAccessToken().getToken());
                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            try {
                                Logg.error(User.this.getClass(), "login graph complete-> " + object.toString());
                                user.setId(object.getString("id"));
                                user.setName(object.getString("name"));
                                user.setAvatar("http://graph.facebook.com/" + id + "/picture?type=large");
                                cacheUtil.saveString(CACHE_NAME_USER, GsonUtil.getGson().toJson(user));
                                Logg.error(User.this.getClass(), "login cache complete-> " + cacheUtil.getString(CACHE_NAME_USER, "fail"));
                                UiObserver.newInstance().notify(ObserverTag.TAG_USER_LOGIN_COMPLETE);
                            } catch (JSONException e) {

                                Logg.error(User.this.getClass(), "login graph error-> " + e.toString());
                            }
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender, birthday");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {

                    Logg.error(User.this.getClass(), "login facebook cancel -> ");
                }

                @Override
                public void onError(FacebookException error) {

                    Logg.error(User.this.getClass(), "login facebook error -> " + error.toString());
                }
            });
        }
    }

    public void logout() {

        cacheUtil.clearAll();
        LoginManager.getInstance().logOut();
        UiObserver.newInstance().notify(ObserverTag.TAG_USER_LOGOUT_EVENT);
    }

    public void setCallbackManager(int requestCode, int responseCode, Intent data) {

        callbackManager.onActivityResult(requestCode, responseCode, data);
    }
}