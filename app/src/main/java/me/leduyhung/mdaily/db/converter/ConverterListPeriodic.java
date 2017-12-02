package me.leduyhung.mdaily.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.leduyhung.mdaily.helper.GsonUtil;
import me.leduyhung.mdaily.module.wallet.Periodic;

/**
 * Created by hungleduy on 11/16/17.
 */

public class ConverterListPeriodic {

    @TypeConverter
    public static ArrayList<Periodic> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Periodic>>() {}.getType();
        return GsonUtil.getGson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Periodic> list) {
        String json = GsonUtil.getGson().toJson(list);
        return json;
    }
}