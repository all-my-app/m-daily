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

import com.leduyhung.loglibrary.Logg;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.module_view.createwallet.CreateWallet;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.event.EventWallet;
import me.leduyhung.mdaily.module.module_view.groupwallet.GroupWallet;
import me.leduyhung.mdaily.module.module_view.period.Period;
import me.leduyhung.mdaily.module.module_view.period_day.PeriodDay;
import me.leduyhung.mdaily.module.module_view.period_month.PeriodMonth;
import me.leduyhung.mdaily.module.wallet.Wallet;

/**
 * Created by hungleduy on 11/14/17.
 */
public class CreateWalletAdapter extends RecyclerView.Adapter {

    public static final int TYPE_COMBOBOX_GROUP = 1;
    public static final int TYPE_COMBOBOX_CURRENCY = 2;
    public static final int TYPE_COMBOBOX_TYPE_EVENT = 3;
    public static final int TYPE_COMBOBOX_PERIOD_EVENT = 4;
    public static final int TYPE_COMBOBOX_PERIOD_DAY = 5;
    public static final int TYPE_COMBOBOX_PERIOD_MONTH = 6;

    private final int TYPE_GENERAL = 111;
    private final int TYPE_EVENT = 112;
    private final int TYPE_ADD = 113;

    private Context mContext;

    private CreateWalletAdapterListener listener;

    public CreateWalletAdapter(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {

            return TYPE_GENERAL;
        } else if (position >= CreateWallet.newInstance().getItems().size()) {

            return TYPE_ADD;
        } else {

            return TYPE_EVENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_GENERAL:
                return new ItemViewGeneral(LayoutInflater.from(mContext).inflate(R.layout.item_create_wallet_general, parent, false));
            case TYPE_EVENT:
                return new ItemViewEvent(LayoutInflater.from(mContext).inflate(R.layout.item_create_wallet_event, parent, false));
            case TYPE_ADD:
                return new ItemViewAdd(LayoutInflater.from(mContext).inflate(R.layout.item_create_wallet_add, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position) == TYPE_GENERAL) {

            ((ItemViewGeneral) holder).eName.setText(CreateWallet.newInstance().getItems().get(0).getName());
            ((ItemViewGeneral) holder).eMoneyInWallet.setText(CreateWallet.newInstance().getItems().get(0).getMoney_in_wallet() == 0 ? "" : CreateWallet.newInstance().getItems().get(0).getMoney_in_wallet() + "");
            ((ItemViewGeneral) holder).eDescription.setText(CreateWallet.newInstance().getItems().get(0).getDescription());
            switch (CreateWallet.newInstance().getItems().get(position).getGroup()) {
                case GroupWallet.GROUP_ID_LOVE:
                    ((ItemViewGeneral) holder).tComboGroup.setText(mContext.getResources().getString(R.string.group_love));
                    break;
                case GroupWallet.GROUP_ID_PERSONAL:
                    ((ItemViewGeneral) holder).tComboGroup.setText(mContext.getResources().getString(R.string.group_personal));
                    break;
                case GroupWallet.GROUP_ID_SAVING:
                    ((ItemViewGeneral) holder).tComboGroup.setText(mContext.getResources().getString(R.string.group_saving));
                    break;
                case GroupWallet.GROUP_ID_TRAVEL:
                    ((ItemViewGeneral) holder).tComboGroup.setText(mContext.getResources().getString(R.string.group_travel));
                    break;
                case GroupWallet.GROUP_ID_WORK:
                    ((ItemViewGeneral) holder).tComboGroup.setText(mContext.getResources().getString(R.string.group_work));
                    break;
                default:
                    ((ItemViewGeneral) holder).tComboGroup.setText(mContext.getResources().getString(R.string.group));
                    break;
            }
            switch (CreateWallet.newInstance().getItems().get(position).getCurrency()) {
                case Currency.CURRENCY_ID_VND:
                    ((ItemViewGeneral) holder).tComboCurrency.setText(mContext.getResources().getString(R.string.vnd));
                    break;
                case Currency.CURRENCY_ID_USD:
                    ((ItemViewGeneral) holder).tComboCurrency.setText(mContext.getResources().getString(R.string.usd));
                    break;
                default:
                    ((ItemViewGeneral) holder).tComboCurrency.setText(mContext.getResources().getString(R.string.currency));
                    break;
            }
            ((ItemViewGeneral) holder).eName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    CreateWallet.newInstance().getItems().get(0).setName(editable.toString());
                }
            });
            ((ItemViewGeneral) holder).eMoneyInWallet.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    if (editable.toString().length() >= 1)
                        CreateWallet.newInstance().getItems().get(0).setMoney_in_wallet(Long.parseLong(editable.toString()));
                    else
                        CreateWallet.newInstance().getItems().get(0).setMoney_in_wallet(0);
                }
            });
            ((ItemViewGeneral) holder).eDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    CreateWallet.newInstance().getItems().get(0).setDescription(editable.toString());
                }
            });
            ((ItemViewGeneral) holder).rGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onComboboxClick(TYPE_COMBOBOX_GROUP, position);
                }
            });
            ((ItemViewGeneral) holder).rCurrency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onComboboxClick(TYPE_COMBOBOX_CURRENCY, position);
                }
            });
        } else if (getItemViewType(position) == TYPE_EVENT) {

            switch (CreateWallet.newInstance().getItems().get(position).getType()) {

                case EventWallet.EVENT_ID_COLLECTION:
                    ((ItemViewEvent) holder).tComboType.setText(mContext.getResources().getString(R.string.collection));
                    break;
                case EventWallet.EVENT_ID_SPENT:
                    ((ItemViewEvent) holder).tComboType.setText(mContext.getResources().getString(R.string.spent));
                    break;
                default:
                    ((ItemViewEvent) holder).tComboType.setText(mContext.getResources().getString(R.string.type));
                    break;
            }

            if (CreateWallet.newInstance().getItems().get(position).getPeriod() == Period.PERIOD_ID_MONTH) {

                ((ItemViewEvent) holder).tComboPeriod.setText(mContext.getResources().getString(R.string.period_month));
                switch (CreateWallet.newInstance().getItems().get(position).getPeriod_day()) {
                    case PeriodDay.PERIOD_DAY_ID_DAY_1:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_1));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_2:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_2));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_3:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_3));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_4:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_4));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_5:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_5));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_6:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_6));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_7:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_7));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_8:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_8));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_9:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_9));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_10:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_10));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_11:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_11));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_12:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_12));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_13:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_13));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_14:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_14));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_15:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_15));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_16:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_16));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_17:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_17));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_18:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_18));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_19:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_19));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_20:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_20));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_21:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_21));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_22:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_22));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_23:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_23));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_24:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_24));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_25:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_25));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_26:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_26));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_27:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_27));
                        break;
                    case PeriodDay.PERIOD_DAY_ID_DAY_28:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_28));
                        break;
                    default:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.day_last_month));
                        break;
                }
            } else {
                ((ItemViewEvent) holder).tComboPeriod.setText(mContext.getResources().getString(R.string.period_year));
                switch (CreateWallet.newInstance().getItems().get(position).getPeriod_month()) {
                    case PeriodMonth.PERIOD_MONTH_ID_JAN:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.jan));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_FEB:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.feb));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_MAR:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.mar));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_APRIL:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.april));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_MAY:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.may));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_JUN:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.jun));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_JULY:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.july));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_AUG:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.aug));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_SEP:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.sep));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_OCT:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.oct));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_NOV:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.nov));
                        break;
                    case PeriodMonth.PERIOD_MONTH_ID_DEC:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.dec));
                        break;
                    default:
                        ((ItemViewEvent) holder).tComboPeriodTime.setText(mContext.getResources().getString(R.string.jan));
                        break;
                }
            }
            ((ItemViewEvent) holder).eMoneyEvent.setTag(position);
            ((ItemViewEvent) holder).eDescription.setTag(position);
            ((ItemViewEvent) holder).eMoneyEvent.setText(CreateWallet.newInstance().getItems().get(position).getMoney_event() == 0 ? "" : CreateWallet.newInstance().getItems().get(position).getMoney_event() + "");
            ((ItemViewEvent) holder).eDescription.setText(CreateWallet.newInstance().getItems().get(position).getDescription() + "");
            ((ItemViewEvent) holder).iRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    removeItem(position);
                }
            });
            ((ItemViewEvent) holder).rType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onComboboxClick(TYPE_COMBOBOX_TYPE_EVENT, position);
                }
            });
            ((ItemViewEvent) holder).rPeriod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onComboboxClick(TYPE_COMBOBOX_PERIOD_EVENT, position);
                }
            });

            ((ItemViewEvent) holder).rPeriodTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onComboboxClick(CreateWallet.newInstance().getItems().get(position).getPeriod() == Period.PERIOD_ID_MONTH ? TYPE_COMBOBOX_PERIOD_DAY : TYPE_COMBOBOX_PERIOD_MONTH, position);
                }
            });
        } else {

            ((ItemViewAdd) holder).iAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    addNewItem();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return CreateWallet.newInstance().getItems().size() + 1;
    }

    private void addNewItem() {

        CreateWallet.newInstance().addNewItem();
        notifyItemInserted(CreateWallet.newInstance().getItems().size());
        listener.onAddItem(getItemCount());
    }

    private void removeItem(int position) {

        CreateWallet.newInstance().removeItemByPosition(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount() - 1);
        listener.onRemoveItem(position, getItemCount());
    }

    private class ItemViewGeneral extends RecyclerView.ViewHolder {

        private EditText eName, eMoneyInWallet, eDescription;
        private RelativeLayout rGroup, rCurrency;
        private TextView tComboGroup, tComboCurrency;

        public ItemViewGeneral(View v) {
            super(v);

            eName = v.findViewById(R.id.etxt_name);
            eMoneyInWallet = v.findViewById(R.id.etxt_money_in_wallet);
            eDescription = v.findViewById(R.id.etxt_description);
            rGroup = v.findViewById(R.id.relative_group);
            rCurrency = v.findViewById(R.id.relative_currency);
            tComboGroup = v.findViewById(R.id.txt_combo_group);
            tComboCurrency = v.findViewById(R.id.txt_combo_currency);
        }
    }

    private class ItemViewEvent extends RecyclerView.ViewHolder {

        private EditText eMoneyEvent, eDescription;
        private ImageView iRemove;
        private RelativeLayout rType, rPeriod, rPeriodTime;
        private TextView tComboType, tComboPeriod, tComboPeriodTime;

        public ItemViewEvent(View v) {
            super(v);
            iRemove = v.findViewById(R.id.img_remove);
            eMoneyEvent = v.findViewById(R.id.etxt_money_event);
            eDescription = v.findViewById(R.id.etxt_description);
            rType = v.findViewById(R.id.relative_type);
            rPeriod = v.findViewById(R.id.relative_period);
            rPeriodTime = v.findViewById(R.id.relative_period_time);
            tComboType = v.findViewById(R.id.txt_combo_type);
            tComboPeriod = v.findViewById(R.id.txt_combo_period);
            tComboPeriodTime = v.findViewById(R.id.txt_combo_period_time);

            EventMonneyTextWatcher eventMonneyTextWatcher = new EventMonneyTextWatcher(eMoneyEvent);
            eMoneyEvent.addTextChangedListener(eventMonneyTextWatcher);

            EventDescriptionTextWatcher eventDescriptionTextWatcher = new EventDescriptionTextWatcher(eDescription);
            eDescription.addTextChangedListener(eventDescriptionTextWatcher);
        }
    }

    private class ItemViewAdd extends RecyclerView.ViewHolder {

        private ImageView iAdd;

        public ItemViewAdd(View v) {
            super(v);
            iAdd = v.findViewById(R.id.img_add);
        }
    }

    private class EventMonneyTextWatcher implements TextWatcher {

        private int position;
        private EditText e;
        public EventMonneyTextWatcher(EditText editText) {

            e = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            position = (int)e.getTag();
            if (CreateWallet.newInstance().getItems().size() > position) {
                if (charSequence.toString().length() >= 1)
                    CreateWallet.newInstance().getItems().get(position).setMoney_event(Long.parseLong(charSequence.toString()));
                else
                    CreateWallet.newInstance().getItems().get(position).setMoney_event(0);
            } else {

                return;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class EventDescriptionTextWatcher implements TextWatcher {

        private int position;
        private EditText e;

        public EventDescriptionTextWatcher(EditText editText) {

            e = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            position = (int)e.getTag();
            if (CreateWallet.newInstance().getItems().size() > position) {
                CreateWallet.newInstance().getItems().get(position).setDescription(charSequence.toString());
            } else {

                return;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    public void setAdapterClickListener(CreateWalletAdapterListener listener) {

        this.listener = listener;
    }

    public interface CreateWalletAdapterListener {
        void onComboboxClick(int type_combobox, int position);

        void onAddItem(int totalItem);

        void onRemoveItem(int position, int totalItem);
    }
}