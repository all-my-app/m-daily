package me.leduyhung.mdaily.module.module_view.period_day;

import android.content.Context;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;

/**
 * Created by hungleduy on 11/16/17.
 */

public class PeriodDay {

    public static final int PERIOD_DAY_ID_LAST_MONTH = 0;
    public static final int PERIOD_DAY_ID_DAY_1 = 1;
    public static final int PERIOD_DAY_ID_DAY_2 = 2;
    public static final int PERIOD_DAY_ID_DAY_3 = 3;
    public static final int PERIOD_DAY_ID_DAY_4 = 4;
    public static final int PERIOD_DAY_ID_DAY_5 = 5;
    public static final int PERIOD_DAY_ID_DAY_6 = 6;
    public static final int PERIOD_DAY_ID_DAY_7 = 7;
    public static final int PERIOD_DAY_ID_DAY_8 = 8;
    public static final int PERIOD_DAY_ID_DAY_9 = 9;
    public static final int PERIOD_DAY_ID_DAY_10 = 10;
    public static final int PERIOD_DAY_ID_DAY_11 = 11;
    public static final int PERIOD_DAY_ID_DAY_12 = 12;
    public static final int PERIOD_DAY_ID_DAY_13 = 13;
    public static final int PERIOD_DAY_ID_DAY_14 = 14;
    public static final int PERIOD_DAY_ID_DAY_15 = 15;
    public static final int PERIOD_DAY_ID_DAY_16 = 16;
    public static final int PERIOD_DAY_ID_DAY_17 = 17;
    public static final int PERIOD_DAY_ID_DAY_18 = 18;
    public static final int PERIOD_DAY_ID_DAY_19 = 19;
    public static final int PERIOD_DAY_ID_DAY_20 = 20;
    public static final int PERIOD_DAY_ID_DAY_21 = 21;
    public static final int PERIOD_DAY_ID_DAY_22 = 22;
    public static final int PERIOD_DAY_ID_DAY_23 = 23;
    public static final int PERIOD_DAY_ID_DAY_24 = 24;
    public static final int PERIOD_DAY_ID_DAY_25 = 25;
    public static final int PERIOD_DAY_ID_DAY_26 = 26;
    public static final int PERIOD_DAY_ID_DAY_27 = 27;
    public static final int PERIOD_DAY_ID_DAY_28 = 28;

    private ArrayList<PeriodDayItem> items;

    public PeriodDay(Context mContext) {
        create(mContext);
    }

    public ArrayList<PeriodDayItem> getItems() {
        return items;
    }

    public void create(Context mContext) {

        items = new ArrayList();
        items.add(new PeriodDayItem(PERIOD_DAY_ID_LAST_MONTH, mContext.getResources().getString(R.string.day_last_month)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_1, mContext.getResources().getString(R.string.day_1)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_2, mContext.getResources().getString(R.string.day_2)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_3, mContext.getResources().getString(R.string.day_3)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_4, mContext.getResources().getString(R.string.day_4)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_5, mContext.getResources().getString(R.string.day_5)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_6, mContext.getResources().getString(R.string.day_6)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_7, mContext.getResources().getString(R.string.day_7)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_8, mContext.getResources().getString(R.string.day_8)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_9, mContext.getResources().getString(R.string.day_9)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_10, mContext.getResources().getString(R.string.day_10)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_11, mContext.getResources().getString(R.string.day_11)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_12, mContext.getResources().getString(R.string.day_12)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_13, mContext.getResources().getString(R.string.day_13)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_14, mContext.getResources().getString(R.string.day_14)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_15, mContext.getResources().getString(R.string.day_15)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_16, mContext.getResources().getString(R.string.day_16)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_17, mContext.getResources().getString(R.string.day_17)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_18, mContext.getResources().getString(R.string.day_18)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_19, mContext.getResources().getString(R.string.day_19)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_20, mContext.getResources().getString(R.string.day_20)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_21, mContext.getResources().getString(R.string.day_21)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_22, mContext.getResources().getString(R.string.day_22)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_23, mContext.getResources().getString(R.string.day_23)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_24, mContext.getResources().getString(R.string.day_24)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_25, mContext.getResources().getString(R.string.day_25)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_26, mContext.getResources().getString(R.string.day_26)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_27, mContext.getResources().getString(R.string.day_27)));
        items.add(new PeriodDayItem(PERIOD_DAY_ID_DAY_28, mContext.getResources().getString(R.string.day_28)));
    }
}