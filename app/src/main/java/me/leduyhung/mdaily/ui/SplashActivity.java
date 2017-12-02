package me.leduyhung.mdaily.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.leduyhung.mdaily.module.user.User;
import me.leduyhung.mdaily.ui.home.HomeActivity;
import me.leduyhung.mdaily.ui.login.LoginActivity;

/**
 * Created by hungleduy on 11/2/17.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();
    }

    private void start() {

        if (!User.newInstance(this).isLogin())
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}