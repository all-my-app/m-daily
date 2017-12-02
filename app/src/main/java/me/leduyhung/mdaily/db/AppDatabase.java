package me.leduyhung.mdaily.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import me.leduyhung.mdaily.db.dao.WalletDao;
import me.leduyhung.mdaily.module.wallet.Wallet;

/**
 * Created by hungleduy on 11/16/17.
 */
@Database(entities = {Wallet.class}, version = DbConstant.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    public abstract WalletDao walletDao();

    public static AppDatabase newInstance(Context context) {

        if (appDatabase == null) {

            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DbConstant.DATABASE_NAME).allowMainThreadQueries().addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                }

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            }).build();
        }

        return appDatabase;
    }

    public static void destroyAppDatabase() {

        appDatabase = null;
    }
}