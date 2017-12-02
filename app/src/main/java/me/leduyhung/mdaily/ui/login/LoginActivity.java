package me.leduyhung.mdaily.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.user.User;
import me.leduyhung.mdaily.observer.UiObserver;
import me.leduyhung.mdaily.observer.listener.UserEvent;
import me.leduyhung.mdaily.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UserEvent.UserLogin {

    private Button bLogin;

    private boolean logining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configView();
        UiObserver.newInstance().listener(this);
    }

    @Override
    public void onClick(View view) {

        login();
    }

    @Override
    public void onLoginComplete() {

        startActivity(new Intent(this, HomeActivity.class));
        logining = false;
    }

    @Override
    public void onLoginCancel() {

        logining = false;
    }

    @Override
    public void onLoginFail() {

        User.newInstance(this).logout();
        logining = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        User.newInstance(this).setCallbackManager(requestCode, resultCode, data);
    }

    private void configView() {

        bLogin = findViewById(R.id.btn_login);
        bLogin.setOnClickListener(this);
    }

    private void login() {

        if (!logining) {
            User.newInstance(this).login(this);
            logining = true;
        }
    }
}