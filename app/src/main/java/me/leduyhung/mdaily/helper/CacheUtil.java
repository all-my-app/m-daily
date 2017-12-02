package me.leduyhung.mdaily.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.leduyhung.loglibrary.Logg;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by hungleduy on 11/3/17.
 */

public class CacheUtil {

    private final String CACHE_NAME = "M-DAILY";
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    public CacheUtil(Context context) {

        preferences = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveString(String key, Object data) {

        editor.putString(key, data.toString());
        editor.apply();
    }

    public String getString(String key, String valueDefault) {

        return preferences.getString(key, valueDefault);
    }

    public void saveInt(String key, int data) {

        editor.putInt(key, data);
        editor.apply();
    }

    public int getInt(String key, int valueDefault) {

        return preferences.getInt(key, valueDefault);
    }

    public void saveBoolean(String key, boolean data) {

        editor.putBoolean(key, data);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean valueDefault) {

        return preferences.getBoolean(key, valueDefault);
    }

    public void clearAll() {

        editor.clear().commit();
    }
}