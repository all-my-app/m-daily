package me.leduyhung.mdaily.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by hungleduy on 12/15/17.
 */

public class ConverterDate {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}