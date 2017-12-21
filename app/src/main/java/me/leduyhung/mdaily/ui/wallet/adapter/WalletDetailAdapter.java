package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.helper.CalendarUtil;
import me.leduyhung.mdaily.helper.NumberConvert;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.event.EventWallet;
import me.leduyhung.mdaily.module.module_view.groupwallet.GroupWallet;
import me.leduyhung.mdaily.module.module_view.period.Period;
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
        }
        return TYPE_EVENT;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_GENERAL) {

            return new ItemGeneral(LayoutInflater.from(mContext).inflate(R.layout.item_wallet_infor_general, parent, false));
        }
        return new ItemEvent(LayoutInflater.from(mContext).inflate(R.layout.item_wallet_infor_event, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_GENERAL) {

            ((ItemGeneral) holder).tName.setText(wallet.getName());
            ((ItemGeneral) holder).tMoney.setText(NumberConvert.newInstance()
                    .convertNumberCurrency(wallet.getMoney(),
                            wallet.getCurrency() == Currency.CURRENCY_ID_VND ? mContext.getResources().getString(R.string.vnd) :
                                    mContext.getResources().getString(R.string.usd)));
            switch (wallet.getGroup()) {
                case GroupWallet.GROUP_ID_WORK:
                    ((ItemGeneral) holder).tGroup.setText(mContext.getResources().getString(R.string.group_work));
                    break;
                case GroupWallet.GROUP_ID_PERSONAL:
                    ((ItemGeneral) holder).tGroup.setText(mContext.getResources().getString(R.string.group_personal));
                    break;
                case GroupWallet.GROUP_ID_LOVE:
                    ((ItemGeneral) holder).tGroup.setText(mContext.getResources().getString(R.string.group_love));
                    break;
                case GroupWallet.GROUP_ID_SAVING:
                    ((ItemGeneral) holder).tGroup.setText(mContext.getResources().getString(R.string.group_saving));
                    break;
                case GroupWallet.GROUP_ID_TRAVEL:
                    ((ItemGeneral) holder).tGroup.setText(mContext.getResources().getString(R.string.group_travel));
                    break;
                default:
                    ((ItemGeneral) holder).tGroup.setText(mContext.getResources().getString(R.string.group_work));
            }
            ((ItemGeneral) holder).tDateCrete.setText(CalendarUtil.newInstance().convertDateToString(wallet.getDay_create()));
            ((ItemGeneral) holder).tDescription.setText(wallet.getDescription());
        } else {

            switch (wallet.getPeriodics().get(position - 1).getType()) {
                case EventWallet.EVENT_ID_COLLECTION:
                    ((ItemEvent) holder).tType.setText(mContext.getResources().getString(R.string.collection));
                    break;
                default:
                    ((ItemEvent) holder).tType.setText(mContext.getResources().getString(R.string.spent));
            }
            ((ItemEvent) holder).tMoney.setText(NumberConvert
                    .newInstance().convertNumberCurrency(wallet.getPeriodics().get(position - 1).getMoney_event(),
                            wallet.getCurrency() == Currency.CURRENCY_ID_VND ? mContext.getResources().getString(R.string.vnd) :
                                    mContext.getResources().getString(R.string.usd)));
            ((ItemEvent) holder).tPeriod.setText(wallet.getPeriodics().get(position - 1).getPeriod() == Period.PERIOD_ID_MONTH ?
                    mContext.getResources().getString(R.string.day) + " " + wallet.getPeriodics().get(position - 1).getPeriod_day() :
                    mContext.getResources().getString(R.string.month) + " " + wallet.getPeriodics().get(position - 1).getPeriod_month());
            ((ItemEvent) holder).tDescription.setText(wallet.getPeriodics().get(position - 1).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        if (wallet != null) {

            if (wallet.getPeriodics() != null)
                return wallet.getPeriodics().size() + 1;
            else
                return 1;
        } else
            return 0;
    }

    public void updateData(Wallet wallet) {

        this.wallet = wallet;
        notifyDataSetChanged();
    }

    private class ItemGeneral extends RecyclerView.ViewHolder {

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

    private class ItemEvent extends RecyclerView.ViewHolder {

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