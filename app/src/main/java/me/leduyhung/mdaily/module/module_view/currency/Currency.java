package me.leduyhung.mdaily.module.module_view.currency;

import android.content.Context;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;

/**
 * Created by hungleduy on 11/15/17.
 */

public class Currency {

    public static final int CURRENCY_ID_VND = 1;
    public static final int CURRENCY_ID_USD = 2;

    private ArrayList<CurrencyItem> items;

    public Currency(Context mContext) {

        createCurrency(mContext);
    }

    public ArrayList<CurrencyItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CurrencyItem> items) {
        this.items = items;
    }

    public void createCurrency(Context mContext) {

        items = new ArrayList();
        items.add(new CurrencyItem(CURRENCY_ID_VND, mContext.getResources().getString(R.string.vnd)));
        items.add(new CurrencyItem(CURRENCY_ID_USD, mContext.getResources().getString(R.string.usd)));
    }
}