package me.leduyhung.mdaily.module.module_view.period_month;

import android.content.Context;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;

/**
 * Created by hungleduy on 11/16/17.
 */

public class PeriodMonth {

    public static final int PERIOD_MONTH_ID_JAN = 1;
    public static final int PERIOD_MONTH_ID_FEB = 2;
    public static final int PERIOD_MONTH_ID_MAR = 3;
    public static final int PERIOD_MONTH_ID_APRIL = 4;
    public static final int PERIOD_MONTH_ID_MAY = 5;
    public static final int PERIOD_MONTH_ID_JUN = 6;
    public static final int PERIOD_MONTH_ID_JULY = 7;
    public static final int PERIOD_MONTH_ID_AUG = 8;
    public static final int PERIOD_MONTH_ID_SEP = 9;
    public static final int PERIOD_MONTH_ID_OCT = 10;
    public static final int PERIOD_MONTH_ID_NOV = 11;
    public static final int PERIOD_MONTH_ID_DEC = 12;

    private ArrayList<PeriodMonthItem> items;

    public PeriodMonth(Context mContext) {

        create(mContext);
    }

    public ArrayList<PeriodMonthItem> getItems() {
        return items;
    }

    public void create(Context mContext) {

        items = new ArrayList();
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_JAN, mContext.getResources().getString(R.string.jan)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_FEB, mContext.getResources().getString(R.string.feb)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_MAR, mContext.getResources().getString(R.string.mar)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_APRIL, mContext.getResources().getString(R.string.april)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_MAY, mContext.getResources().getString(R.string.may)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_JUN, mContext.getResources().getString(R.string.jun)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_JULY, mContext.getResources().getString(R.string.july)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_AUG, mContext.getResources().getString(R.string.aug)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_SEP, mContext.getResources().getString(R.string.sep)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_OCT, mContext.getResources().getString(R.string.oct)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_NOV, mContext.getResources().getString(R.string.nov)));
        items.add(new PeriodMonthItem(PERIOD_MONTH_ID_DEC, mContext.getResources().getString(R.string.dec)));
    }
}