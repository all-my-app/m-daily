package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.helper.CalendarUtil;
import me.leduyhung.mdaily.module.wallet.Bill;

/**
 * Created by hungleduy on 12/5/17.
 */

public class ListPaidWalletAdapter extends RecyclerView.Adapter {

    private final int ITEM_NO_DATA = 1000;
    private final int ITEM_HAS_DATA = 1001;

    private Context mContext;
    private ArrayList<Bill> arrData;

    public ListPaidWalletAdapter(Context context, ArrayList<Bill> arrData) {

        this.mContext = context;
        this.arrData = arrData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (arrData.size() > 0)
            return new ItemView(LayoutInflater.from(mContext).inflate(R.layout.item_list_paid_wallet, parent, false));
        else
            return new ItemNoData(LayoutInflater.from(mContext).inflate(R.layout.layout_recycler_no_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (arrData.size() > 0) {
            ((ItemView) holder).tTime.setText(CalendarUtil.newInstance().convertDateToString(arrData.get(position).getPayDate()));
            ((ItemView) holder).tMoneyChange.setText(arrData.get(position).getChangeMoney() + "");
            ((ItemView) holder).tMoneyOld.setText(arrData.get(position).getOldMoney() + "");
            ((ItemView) holder).tMoneyNew.setText((arrData.get(position).getOldMoney() + arrData.get(position).getChangeMoney()) + "");
            ((ItemView) holder).tDescription.setText(arrData.get(position).getDescription());
            ((ItemView) holder).iShare.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_share));
            if (arrData.get(position).getChangeMoney() >= 0) {

                ((ItemView) holder).tStatus.setText(mContext.getResources().getString(R.string.collection));
                ((ItemView) holder).reBarCard.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            } else {

                ((ItemView) holder).tStatus.setText(mContext.getResources().getString(R.string.spent));
                ((ItemView) holder).reBarCard.setBackgroundColor(mContext.getResources().getColor(R.color.colorRed));
            }
        } else {

        }
    }

    @Override
    public int getItemCount() {
        if (arrData.size() == 0)
            return 1;
        return arrData.size();
    }

    public void updateListPaid(ArrayList<Bill> data) {

        arrData.clear();
        arrData.addAll(data);
        notifyDataSetChanged();
    }

    private static class ItemView extends RecyclerView.ViewHolder {

        private RelativeLayout reBarCard;
        private TextView tTime, tStatus, tMoneyChange, tMoneyOld, tMoneyNew, tDescription;
        private ImageView iShare;

        public ItemView(View v) {
            super(v);

            reBarCard = v.findViewById(R.id.bar_card);
            iShare = v.findViewById(R.id.img_share);
            tTime = v.findViewById(R.id.txt_time);
            tStatus = v.findViewById(R.id.txt_status);
            tMoneyChange = v.findViewById(R.id.txt_money_change);
            tMoneyOld = v.findViewById(R.id.txt_money_old);
            tMoneyNew = v.findViewById(R.id.txt_money_new);
            tDescription = v.findViewById(R.id.txt_description);
        }
    }

    private static class ItemNoData extends RecyclerView.ViewHolder {

        public ItemNoData(View itemView) {
            super(itemView);
        }
    }
}