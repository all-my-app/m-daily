package me.leduyhung.mdaily;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.leduyhung.loglibrary.Logg;

/**
 * Created by hungleduy on 11/2/17.
 */

public class DailyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        Logg.init(getString(R.string.app_name), true);
    }
}