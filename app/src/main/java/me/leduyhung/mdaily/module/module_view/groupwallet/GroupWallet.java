package me.leduyhung.mdaily.module.module_view.groupwallet;

import android.content.Context;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;

/**
 * Created by hungleduy on 11/13/17.
 */

public class GroupWallet {

    public static final int GROUP_ID_WORK = 1;
    public static final int GROUP_ID_PERSONAL = 2;
    public static final int GROUP_ID_LOVE = 3;
    public static final int GROUP_ID_SAVING = 4;
    public static final int GROUP_ID_TRAVEL = 5;

    private ArrayList<GroupItem> item;

    public GroupWallet(Context mContext) {

        createGroupWallet(mContext);
    }

    public ArrayList<GroupItem> getItem() {
        return item;
    }

    public void setItem(ArrayList<GroupItem> item) {
        this.item = item;
    }

    public void createGroupWallet(Context mContext) {

        item = new ArrayList();
        item.add(new GroupItem(GROUP_ID_WORK, mContext.getResources().getString(R.string.group_work)));
        item.add(new GroupItem(GROUP_ID_PERSONAL, mContext.getResources().getString(R.string.group_personal)));
        item.add(new GroupItem(GROUP_ID_LOVE, mContext.getResources().getString(R.string.group_love)));
        item.add(new GroupItem(GROUP_ID_SAVING, mContext.getResources().getString(R.string.group_saving)));
        item.add(new GroupItem(GROUP_ID_TRAVEL, mContext.getResources().getString(R.string.group_travel)));
    }
}