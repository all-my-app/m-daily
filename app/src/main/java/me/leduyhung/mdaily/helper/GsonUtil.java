package me.leduyhung.mdaily.helper;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by hungleduy on 11/3/17.
 */

public class GsonUtil {

    private static Gson gson;

    public static Gson getGson() {

        if (gson == null) {

            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        }
        return gson;
    }
}