package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

//import com.leduyhung.chartlibrary.line.ChartLineView;

import com.leduyhung.loglibrary.Logg;

import java.util.ArrayList;

import leduyhung.view.mychart.line.ChartLineView;
import leduyhung.view.myspinner.MySpinnerView;
import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.helper.NumberConvert;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.groupwallet.GroupWallet;
import me.leduyhung.mdaily.module.wallet.Wallet;
import me.leduyhung.mdaily.ui.wallet.WalletDetailActivity;

/**
 * Created by hungleduy on 11/7/17.
 */

public class ListWalletAdapter extends RecyclerView.Adapter {

    private final int TYPE_NO_ITEM = 10000;
    private final int TYPE_ITEM = 10001;

    private Context mContext;
    private ArrayList<Wallet> arrData;

    public ListWalletAdapter(Context context, ArrayList<Wallet> arrData) {

        this.arrData = arrData;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (arrData.get(position) == null)
            return TYPE_NO_ITEM;
        return TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
            return new ItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wallet, parent, false));
        return new ItemNoData(LayoutInflater.from(mContext).inflate(R.layout.layout_recycler_no_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemView && arrData.size() > 0) {
            ((ItemView) holder).tTitle.setText(arrData.get(position).getName());
            if (arrData.get(position).getStatus() == Wallet.WALLET_STATUS_NO_CHANGE) {

                ((ItemView) holder).tStatus.setText(mContext.getResources().getString(R.string.wallet_no_change));
            } else if (arrData.get(position).getStatus() == Wallet.WALLET_STATUS_INCREASE) {

                ((ItemView) holder).tStatus.setText(mContext.getResources().getString(R.string.wallet_increase));
            } else {

                ((ItemView) holder).tStatus.setText(mContext.getResources().getString(R.string.wallet_decrease));
            }

            switch (arrData.get(position).getGroup()) {
                case GroupWallet.GROUP_ID_LOVE:
                    ((ItemView) holder).iTitle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet_love));
                    break;
                case GroupWallet.GROUP_ID_PERSONAL:
                    ((ItemView) holder).iTitle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet_personal));
                    break;
                case GroupWallet.GROUP_ID_SAVING:
                    ((ItemView) holder).iTitle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet_saving));
                    break;
                case GroupWallet.GROUP_ID_TRAVEL:
                    ((ItemView) holder).iTitle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet_travel));
                    break;
                case GroupWallet.GROUP_ID_WORK:
                    ((ItemView) holder).iTitle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet_work));
                    break;
                default:
                    ((ItemView) holder).iTitle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wallet_love));
                    break;
            }
            ((ItemView) holder).tMoney.setText(NumberConvert
                    .newInstance().convertNumberCurrency(arrData.get(position).getMoney(),
                            arrData.get(position).getCurrency() == Currency.CURRENCY_ID_VND ? mContext.getResources().getString(R.string.vnd) :
                                    mContext.getResources().getString(R.string.usd)));
            ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, WalletDetailActivity.class);
                    intent.putExtra(Constant.ListWallet.KEY_INTENT_ID_WALLET, arrData.get(position).getId());
                    mContext.startActivity(intent);
                }
            });
        } else {

            return;
        }
    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }

    private class ItemView extends RecyclerView.ViewHolder {

        private LinearLayout lItem;
        private ImageView iTitle, iMenu;
        private TextView tTitle, tStatus, tMoney;
        private MySpinnerView spin;
        private ChartLineView chart;

        public ItemView(View v) {
            super(v);

            lItem = v.findViewById(R.id.linear_item);
            iTitle = v.findViewById(R.id.img_title_left);
            tTitle = v.findViewById(R.id.txt_item_title);
            tStatus = v.findViewById(R.id.txt_status);
            tMoney = v.findViewById(R.id.txt_money);
            spin = v.findViewById(R.id.mSpin_item);
            chart = v.findViewById(R.id.chartlineview);
        }
    }

    private class ItemNoData extends RecyclerView.ViewHolder {
        public ItemNoData(View itemView) {
            super(itemView);
        }
    }
}