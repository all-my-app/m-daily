package me.leduyhung.mdaily.ui.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.module_view.createpaidwallet.CreatePaidWallet;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.event.EventWallet;

/**
 * Created by hungleduy on 12/19/17.
 */

public class CreatePaidWalletAdapter extends RecyclerView.Adapter {

    private final int TYPE_ITEM = 111;
    private final int TYPE_ADD = 112;

    public static final int TYPE_COMBOBOX_TYPE = 1;
    public static final int TYPE_COMBOBOX_CURRENCY = 2;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemView) {

            switch (CreatePaidWallet.newInstance().getItems().get(position).getCurrency()) {
                case Currency.CURRENCY_ID_VND:
                    ((ItemView) holder).tComboCurrency.setText(mContext.getResources().getString(R.string.vnd));
                    break;
                case Currency.CURRENCY_ID_USD:
                    ((ItemView) holder).tComboCurrency.setText(mContext.getResources().getString(R.string.usd));
                    break;
                default:
                    ((ItemView) holder).tComboCurrency.setText(mContext.getResources().getString(R.string.currency));
                    break;
            }
            switch (CreatePaidWallet.newInstance().getItems().get(position).getType()) {

                case EventWallet.EVENT_ID_COLLECTION:
                    ((ItemView) holder).tComboType.setText(mContext.getResources().getString(R.string.collection));
                    break;
                case EventWallet.EVENT_ID_SPENT:
                    ((ItemView) holder).tComboType.setText(mContext.getResources().getString(R.string.spent));
                    break;
                default:
                    ((ItemView) holder).tComboType.setText(mContext.getResources().getString(R.string.type));
                    break;
            }
            ((ItemView)holder).eMoney.setTag(position);
            ((ItemView)holder).eDescription.setTag(position);
            ((ItemView) holder).eMoney.setText(CreatePaidWallet.newInstance().getItems().get(position).getMoney_event() == 0 ? "" :
                    CreatePaidWallet.newInstance().getItems().get(position).getMoney_event() + "");
            ((ItemView) holder).eDescription.setText(CreatePaidWallet.newInstance().getItems().get(position).getDescription() + "");
            ((ItemView)holder).iRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    removeItem(position);
                }
            });
            ((ItemView)holder).rType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onComboboxClick(TYPE_COMBOBOX_TYPE, position);
                }
            });
            ((ItemView)holder).rCurrency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onComboboxClick(TYPE_COMBOBOX_CURRENCY, position);
                }
            });
        } else {

            ((ItemAdd) holder).iAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    addNewItem();
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return CreatePaidWallet.newInstance().getItems().size() + 1;
    }

    private class ItemView extends RecyclerView.ViewHolder {

        private TextView tDate, tComboType, tComboCurrency;
        private ImageView iRemove;
        private EditText eMoney, eDescription;
        private RelativeLayout rType, rCurrency;

        public ItemView(View itemView) {
            super(itemView);

            tDate = itemView.findViewById(R.id.txt_date_create);
            iRemove = itemView.findViewById(R.id.img_remove);
            eMoney = itemView.findViewById(R.id.etxt_money_event);
            eDescription = itemView.findViewById(R.id.etxt_description);
            rType = itemView.findViewById(R.id.relative_type);
            rCurrency = itemView.findViewById(R.id.relative_currency);
            tComboCurrency = itemView.findViewById(R.id.txt_combo_currency);
            tComboType = itemView.findViewById(R.id.txt_combo_type);

            MonneyTextWatcher monneyTextWatcher = new MonneyTextWatcher(eMoney);
            eMoney.addTextChangedListener(monneyTextWatcher);

            DescriptionTextWatcher descriptionTextWatcher = new DescriptionTextWatcher(eDescription);
            eDescription.addTextChangedListener(descriptionTextWatcher);
        }
    }

    private class ItemAdd extends RecyclerView.ViewHolder {

        private ImageView iAdd;

        public ItemAdd(View itemView) {
            super(itemView);

            iAdd = itemView.findViewById(R.id.img_add);
        }
    }

    private class MonneyTextWatcher implements TextWatcher {

        private int position;
        private EditText e;

        public MonneyTextWatcher(EditText editText) {

            e = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            position = (int) e.getTag();
            if (CreatePaidWallet.newInstance().getItems().size() > position) {
                if (charSequence.toString().length() >= 1)
                    CreatePaidWallet.newInstance().getItems().get(position).setMoney_event(Long.parseLong(charSequence.toString()));
                else
                    CreatePaidWallet.newInstance().getItems().get(position).setMoney_event(0);
            } else {

                return;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class DescriptionTextWatcher implements TextWatcher {

        private int position;
        private EditText e;

        public DescriptionTextWatcher(EditText editText) {

            e = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            position = (int) e.getTag();
            if (CreatePaidWallet.newInstance().getItems().size() > position) {
                CreatePaidWallet.newInstance().getItems().get(position).setDescription(charSequence.toString());
            } else {

                return;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private void addNewItem() {

        CreatePaidWallet.newInstance().addNewItem();
        notifyItemInserted(CreatePaidWallet.newInstance().getItems().size());
        listener.onAddItem(getItemCount());
    }

    private void removeItem(int position) {

        CreatePaidWallet.newInstance().removeItemByPosition(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - 1);
        listener.onRemoveItem(position, getItemCount());
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