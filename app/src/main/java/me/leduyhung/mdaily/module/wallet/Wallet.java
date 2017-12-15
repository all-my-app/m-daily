package me.leduyhung.mdaily.module.wallet;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import me.leduyhung.mdaily.db.AppDatabase;
import me.leduyhung.mdaily.db.DbConstant;
import me.leduyhung.mdaily.db.converter.ConverterDate;
import me.leduyhung.mdaily.db.converter.ConverterListPeriodic;
import me.leduyhung.mdaily.db.converter.ConverterListStatistical;
import me.leduyhung.mdaily.helper.CalendarUtil;

/**
 * Created by hungleduy on 11/9/17.
 */
@Entity(tableName = DbConstant.TABLE_WALLET, primaryKeys = {"id"})
public class Wallet {

    @Ignore
    public static final int WALLET_STATUS_NO_CHANGE = 0;
    @Ignore
    public static final int WALLET_STATUS_INCREASE = 1;
    @Ignore
    public static final int WALLET_STATUS_DECREASE = 2;

    private int id;
    private String name;
    private String description;
    @TypeConverters(ConverterListStatistical.class)
    private ArrayList<Statistical> statistics;
    private long money;
    private int currency;
    private int group;
    private int status;
    @TypeConverters(ConverterDate.class)
    private Date day_create;
    @TypeConverters(ConverterListPeriodic.class)
    private ArrayList<Periodic> periodics;

    public Wallet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Statistical> getStatistics() {
        return statistics;
    }

    public void setStatistics(ArrayList<Statistical> statistics) {
        if (statistics != null)
            this.statistics = statistics;
        else {

            statistics = new ArrayList<>();
            statistics.add(new Statistical(CalendarUtil.newInstance().getCurrentYear(), money, null));
        }
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int type) {
        this.group = type;
    }

    public ArrayList<Periodic> getPeriodics() {
        return periodics;
    }

    public void setPeriodics(ArrayList<Periodic> periodics) {
        this.periodics = periodics;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public void createWallet(Context mContext, Wallet wallet) {

        AppDatabase.newInstance(mContext).walletDao().insertWallet(wallet);
    }

    public Date getDay_create() {
        return day_create;
    }

    public void setDay_create(Date day_create) {
        this.day_create = day_create;
    }
}