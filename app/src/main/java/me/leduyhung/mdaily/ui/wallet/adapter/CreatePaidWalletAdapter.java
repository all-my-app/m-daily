package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.module_view.createpaidwallet.CreatePaidWallet;

/**
 * Created by hungleduy on 12/19/17.
 */

public class CreatePaidWalletAdapter extends RecyclerView.Adapter {

    private final int TYPE_ITEM = 111;
    private final int TYPE_ADD = 112;

    private Context mContext;

    private CreatePaidWalletAdapterListener listener;

    public CreatePaidWalletAdapter(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= CreatePaidWallet.newInstance().getItems().size())
            return TYPE_ADD;
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
            return new ItemView(LayoutInflater.from(mContext).inflate(R.layout.item_create_paid_wallet, parent, false));
        return new ItemAdd(LayoutInflater.from(mContext).inflate(R.layout.item_create_wallet_add, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemView) {

        } else {

        }
    }

    @Override
    public int getItemCount() {

        return CreatePaidWallet.newInstance().getItems().size() + 1;
    }

    private static class ItemView extends RecyclerView.ViewHolder {

        private TextView tDate;
        private ImageView iRemove;
        private EditText etxtMoney, etxtDescription;
        private RelativeLayout rType, rCurrency;

        public ItemView(View itemView) {
            super(itemView);

            tDate = itemView.findViewById(R.id.txt_date_create);
            iRemove = itemView.findViewById(R.id.img_remove);
            etxtMoney = itemView.findViewById(R.id.etxt_money_event);
            etxtDescription = itemView.findViewById(R.id.etxt_description);
            rType = itemView.findViewById(R.id.relative_type);
            rCurrency = itemView.findViewById(R.id.relative_currency);
        }
    }

    private static class ItemAdd extends RecyclerView.ViewHolder {

        private ImageView iAdd;

        public ItemAdd(View itemView) {
            super(itemView);

            iAdd = itemView.findViewById(R.id.img_add);
        }
    }

    private void addNewItem() {

        CreatePaidWallet.newInstance().addNewItem();
        notifyItemInserted(CreatePaidWallet.newInstance().getItems().size());
        listener.onAddItem(getItemCount());
    }

    public void setAdapterClickListener(CreatePaidWalletAdapterListener listener) {

        this.listener = listener;
    }

    public interface CreatePaidWalletAdapterListener {

        void onComboboxClick(int type_combobox, int position);

        void onAddItem(int totalItem);

        void onRemoveItem(int position, int totalItem);
    }
}