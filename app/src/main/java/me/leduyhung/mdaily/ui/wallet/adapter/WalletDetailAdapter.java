package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.helper.NumberConvert;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.wallet.Wallet;

/**
 * Created by hungleduy on 12/14/17.
 */

public class WalletDetailAdapter extends RecyclerView.Adapter {

    private final int TYPE_GENERAL = 10000;
    private final int TYPE_EVENT = 10001;

    private Context mContext;
    private Wallet wallet;

    public WalletDetailAdapter(Context mContext, Wallet wallet) {
        this.mContext = mContext;
        this.wallet = wallet;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {

            return TYPE_GENERAL;
        } else {

            return TYPE_EVENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_GENERAL) {

            return new ItemGeneral(LayoutInflater.from(mContext).inflate(R.layout.item_wallet_infor_general, parent, false));
        } else {

            return new ItemEvent(LayoutInflater.from(mContext).inflate(R.layout.item_wallet_infor_event, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_GENERAL) {

            ((ItemGeneral)holder).tName.setText(wallet.getName());
            ((ItemGeneral)holder).tMoney.setText(NumberConvert.newInstance()
                    .convertNumberCurrency(wallet.getMoney(),
                            wallet.getCurrency() == Currency.CURRENCY_ID_VND ? mContext.getResources().getString(R.string.vnd) :
                                    mContext.getResources().getString(R.string.usd)));
            ((ItemGeneral)holder).tGroup.setText(wallet.getGroup() + "");
//            ((ItemGeneral)holder).tDateCrete.setText(wallet.get);
            ((ItemGeneral)holder).tDescription.setText(wallet.getDescription());
        } else {

            ((ItemEvent)holder).tType.setText(wallet.getPeriodics().get(position - 1).getType() + "");
            ((ItemEvent)holder).tMoney.setText(NumberConvert
                    .newInstance().convertNumberCurrency(wallet.getPeriodics().get(position - 1).getMoney_event(),
                            wallet.getCurrency() == Currency.CURRENCY_ID_VND ? mContext.getResources().getString(R.string.vnd) :
                    mContext.getResources().getString(R.string.usd)));
            ((ItemEvent)holder).tPeriod.setText(wallet.getPeriodics().get(position - 1).getPeriod() + "");
            ((ItemEvent)holder).tDescription.setText(wallet.getPeriodics().get(position - 1).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        if (wallet != null && wallet.getPeriodics() != null) {

            return wallet.getPeriodics().size() + 1;
        } else
            return 1;
    }

    private static class ItemGeneral extends RecyclerView.ViewHolder {

        private TextView tName, tGroup, tMoney, tDateCrete, tDescription;

        public ItemGeneral(View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.txt_name);
            tGroup = itemView.findViewById(R.id.txt_group);
            tMoney = itemView.findViewById(R.id.txt_money);
            tDateCrete = itemView.findViewById(R.id.txt_date_create);
            tDescription = itemView.findViewById(R.id.txt_description);
        }
    }

    private static class ItemEvent extends RecyclerView.ViewHolder {

        private TextView tType, tMoney, tPeriod, tDescription;

        public ItemEvent(View itemView) {
            super(itemView);
            tType = itemView.findViewById(R.id.txt_type);
            tMoney = itemView.findViewById(R.id.txt_money);
            tPeriod = itemView.findViewById(R.id.txt_period);
            tDescription = itemView.findViewById(R.id.txt_description);

        }
    }
}