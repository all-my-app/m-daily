package me.leduyhung.mdaily.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.leduyhung.mdaily.helper.GsonUtil;
import me.leduyhung.mdaily.module.wallet.Statistical;

/**
 * Created by hungleduy on 11/16/17.
 */

public class ConverterListStatistical {

    @TypeConverter
    public static ArrayList<Statistical> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Statistical>>() {}.getType();
        return GsonUtil.getGson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Statistical> list) {
        String json = GsonUtil.getGson().toJson(list);
        return json;
    }
}