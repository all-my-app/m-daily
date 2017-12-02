package me.leduyhung.mdaily.module.wallet;

import java.util.ArrayList;

/**
 * Created by hungleduy on 11/9/17.
 */

public class Statistical {

    private int year;
    private long coreMoney;
    private ArrayList<Bill> bills;

    public Statistical() {
    }

    public Statistical(int year, long coreMoney, ArrayList<Bill> bills) {
        this.year = year;
        this.coreMoney = coreMoney;
        this.bills = bills;
    }

    public long getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public long getCoreMoney() {
        return coreMoney;
    }

    public void setCoreMoney(long coreMoney) {
        this.coreMoney = coreMoney;
    }
}