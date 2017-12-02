package me.leduyhung.mdaily.module.module_view.event;

import android.content.Context;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;

/**
 * Created by hungleduy on 11/14/17.
 */

public class EventWallet {

    public static final int EVENT_ID_COLLECTION = 1;
    public static final int EVENT_ID_SPENT = 2;

    private ArrayList<EventItem> item;

    public EventWallet(Context mContext) {
        createEventWallet(mContext);
    }

    public ArrayList<EventItem> getItem() {
        return item;
    }

    public void setItem(ArrayList<EventItem> item) {
        this.item = item;
    }

    private void createEventWallet(Context mContext) {

        item = new ArrayList();
        item.add(new EventItem(EVENT_ID_COLLECTION, mContext.getResources().getString(R.string.collection)));
        item.add(new EventItem(EVENT_ID_SPENT, mContext.getResources().getString(R.string.spent)));
    }
}