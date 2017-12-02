package me.leduyhung.mdaily.module.module_view.period;

import android.content.Context;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;

/**
 * Created by hungleduy on 11/15/17.
 */

public class Period {

    public static final int PERIOD_ID_MONTH = 0;
    public static final int CURRENCY_ID_YEAR = 1;

    private ArrayList<PeriodItem> items;

    public Period(Context mContext) {

        createPeriod(mContext);
    }

    public ArrayList<PeriodItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<PeriodItem> items) {
        this.items = items;
    }

    public void createPeriod(Context mContext) {

        items = new ArrayList();
        items.add(new PeriodItem(PERIOD_ID_MONTH, mContext.getResources().getString(R.string.period_month)));
        items.add(new PeriodItem(CURRENCY_ID_YEAR, mContext.getResources().getString(R.string.period_year)));
    }
}