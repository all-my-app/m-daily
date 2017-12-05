package me.leduyhung.mdaily.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.leduyhung.mdaily.db.DbConstant;
import me.leduyhung.mdaily.module.wallet.Wallet;

/**
 * Created by hungleduy on 11/16/17.
 */
@Dao
public interface WalletDao {

    @Query("SELECT * FROM " + DbConstant.TABLE_WALLET)
    LiveData<List<Wallet>> getAllDataWallet();

    @Query("SELECT * FROM " + DbConstant.TABLE_WALLET + " WHERE id IN (:id)")
    LiveData<Wallet> getWalletById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWallet(Wallet... wallet);
}