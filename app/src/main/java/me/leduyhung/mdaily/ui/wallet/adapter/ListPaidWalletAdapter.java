package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.wallet.Bill;

/**
 * Created by hungleduy on 12/5/17.
 */

public class ListPaidWalletAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Bill> arrData;

    public ListPaidWalletAdapter(Context context, ArrayList<Bill> arrData) {

        this.mContext = context;
        this.arrData = arrData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(LayoutInflater.from(mContext).inflate(R.layout.item_list_paid_wallet, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }

    public void updateListPaid(ArrayList<Bill> data) {

        arrData.clear();
        arrData.addAll(data);
        notifyDataSetChanged();
    }

    private static class ItemView extends RecyclerView.ViewHolder {

        private RelativeLayout reBarCard;
        private TextView tTime, tStatus, tMoneyChange, tMoneyOld, tMoneyNew;
        private ImageView iShare;

        public ItemView(View v) {
            super(v);

            reBarCard = v.findViewById(R.id.bar_card);
            iShare = v.findViewById(R.id.img_share);
            tTime = v.findViewById(R.id.txt_time);
            tMoneyChange = v.findViewById(R.id.txt_money_change);
            tMoneyOld = v.findViewById(R.id.txt_money_old);
            tMoneyNew = v.findViewById(R.id.txt_money_new);
        }
    }
}