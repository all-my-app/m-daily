package me.leduyhung.mdaily.ui.wallet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leduyhung.loglibrary.Logg;

import java.util.ArrayList;

import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.dialog.DialogSelectItem;
import me.leduyhung.mdaily.helper.CalendarUtil;
import me.leduyhung.mdaily.module.module_view.createwallet.CreateWallet;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.event.EventWallet;
import me.leduyhung.mdaily.module.module_view.groupwallet.GroupWallet;
import me.leduyhung.mdaily.module.module_view.period.Period;
import me.leduyhung.mdaily.module.module_view.period_day.PeriodDay;
import me.leduyhung.mdaily.module.module_view.period_month.PeriodMonth;
import me.leduyhung.mdaily.module.wallet.Periodic;
import me.leduyhung.mdaily.module.wallet.Wallet;
import me.leduyhung.mdaily.ui.wallet.adapter.CreateWalletAdapter;

public class CreateWalletActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iActionLeft;
    private BottomSheetBehavior bottomView;
    private TextView tCreate, tActionTitle;
    private View bottomSheet;
    private RecyclerView recycler;
    private CreateWalletAdapter adap;
    private DialogSelectItem dialogSelectItem;
    private LinearLayoutManager manager;

    private Currency currency;
    private EventWallet eventWallet;
    private GroupWallet groupWallet;
    private Period period;
    private PeriodDay periodDay;
    private PeriodMonth periodMonth;
    private int stateBottomView;
    private int idWallet, totalWallet;

    @Override
    protected void onStart() {
        super.onStart();

        initDataCombo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);
        initView();
        idWallet = getIntent().getIntExtra(Constant.ListWallet.KEY_INTENT_ID_NEXT_WALLET, 0);
        totalWallet = getIntent().getIntExtra(Constant.ListWallet.KEY_INTENT_TOTAL_WALLET, 0);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_left:
                finish();
                break;
            case R.id.txt_create:

                if (totalWallet <= 100)
                    createWallet();
                else
                    Toast.makeText(this, getResources().getString(R.string.message_wallet_limmit), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        CreateWallet.newInstance().destroy();
        dialogSelectItem.dismiss();
    }

    private void initDataCombo() {

        currency = new Currency(this);
        eventWallet = new EventWallet(this);
        groupWallet = new GroupWallet(this);
        period = new Period(this);
        periodDay = new PeriodDay(this);
        periodMonth = new PeriodMonth(this);
    }

    private void initView() {

        iActionLeft = findViewById(R.id.img_left);
        tActionTitle = findViewById(R.id.txt_title);
        bottomSheet = findViewById(R.id.create);
        tCreate = findViewById(R.id.txt_create);
        recycler = findViewById(R.id.recycler);
        tCreate.setOnClickListener(this);
        dialogSelectItem = new DialogSelectItem(this);
        configActionBar();
        configBottomView();
        configRecycler();
    }

    private void configActionBar() {

        iActionLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        tActionTitle.setText(getResources().getString(R.string.create_wallet));

        iActionLeft.setOnClickListener(this);
    }

    private void configRecycler() {

        adap = new CreateWalletAdapter(this);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adap);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int pastVisibleItems = manager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount && totalItemCount > 2) {

                    bottomView.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if (dy < 0) {

                        if (stateBottomView != 2)
                            bottomView.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else if (dy > 0) {

                        if (stateBottomView != 2) {
                            bottomView.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    }
                }
            }
        });

        adap.setAdapterClickListener(new CreateWalletAdapter.CreateWalletAdapterListener() {
            @Override
            public void onComboboxClick(int type_combobox, int position) {

                switch (type_combobox) {
                    case CreateWalletAdapter.TYPE_COMBOBOX_GROUP:
                        showDialogSelectGroup(position);
                        break;
                    case CreateWalletAdapter.TYPE_COMBOBOX_CURRENCY:
                        showDialogSelectCurrency(position);
                        break;
                    case CreateWalletAdapter.TYPE_COMBOBOX_TYPE_EVENT:
                        showDialogSelectType(position);
                        break;
                    case CreateWalletAdapter.TYPE_COMBOBOX_PERIOD_EVENT:
                        showDialogSelectPeriod(position);
                        break;
                    case CreateWalletAdapter.TYPE_COMBOBOX_PERIOD_DAY:
                        showDialogSelectPeriodDay(position);
                        break;
                    case CreateWalletAdapter.TYPE_COMBOBOX_PERIOD_MONTH:
                        showDialogSelectPeriodMonth(position);
                        break;
                }
            }

            @Override
            public void onAddItem(int totalItem) {
                recycler.smoothScrollToPosition(totalItem - 1);
            }

            @Override
            public void onRemoveItem(int position, int totalItem) {

                if (totalItem <= 2) {

                    bottomView.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }

    private void configBottomView() {

        bottomView = BottomSheetBehavior.from(bottomSheet);
        bottomView.setPeekHeight(0);
        bottomView.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomView.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

                stateBottomView = newState;
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
    }

    private void showDialogSelectGroup(final int position) {

        dialogSelectItem.showDialog(groupWallet.getItem(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreateWalletActivity.this.getClass(), "showDialogSelectGroup -> id =" + id + " -- name=" + name);
                CreateWallet.newInstance().getItems().get(position).setGroup(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void showDialogSelectCurrency(final int position) {

        dialogSelectItem.showDialog(currency.getItems(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreateWalletActivity.this.getClass(), "showDialogSelectCurrency -> id =" + id + " -- name=" + name);
                CreateWallet.newInstance().getItems().get(position).setCurrency(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void showDialogSelectType(final int position) {

        dialogSelectItem.showDialog(eventWallet.getItem(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreateWalletActivity.this.getClass(), "showDialogSelectType -> id =" + id + " -- name=" + name);
                CreateWallet.newInstance().getItems().get(position).setType(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void showDialogSelectPeriod(final int position) {

        dialogSelectItem.showDialog(period.getItems(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreateWalletActivity.this.getClass(), "showDialogSelectPeriod -> id =" + id + " -- name=" + name);
                CreateWallet.newInstance().getItems().get(position).setPeriod(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void showDialogSelectPeriodDay(final int position) {

        dialogSelectItem.showDialog(periodDay.getItems(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreateWalletActivity.this.getClass(), "showDialogSelectPeriodDay -> id =" + id + " -- name=" + name);
                CreateWallet.newInstance().getItems().get(position).setPeriod_day(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void showDialogSelectPeriodMonth(final int position) {

        dialogSelectItem.showDialog(periodMonth.getItems(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreateWalletActivity.this.getClass(), "showDialogSelectPeriodMonth -> id =" + id + " -- name=" + name);
                CreateWallet.newInstance().getItems().get(position).setPeriod_month(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void createWallet() {

        if (CreateWallet.newInstance().getItems().get(0).getName() != null && CreateWallet.newInstance().getItems().get(0).getName() != "" &&
                CreateWallet.newInstance().getItems().get(0).getCurrency() > 0 && CreateWallet.newInstance().getItems().get(0).getGroup() > 0) {
            Wallet wallet = new Wallet();
            int totalItem = CreateWallet.newInstance().getItems().size();
            wallet.setId(idWallet);
            wallet.setName(CreateWallet.newInstance().getItems().get(0).getName());
            wallet.setCurrency(CreateWallet.newInstance().getItems().get(0).getCurrency());
            wallet.setGroup(CreateWallet.newInstance().getItems().get(0).getGroup());
            wallet.setMoney(CreateWallet.newInstance().getItems().get(0).getMoney_in_wallet());
            wallet.setDescription(CreateWallet.newInstance().getItems().get(0).getDescription());
            wallet.setStatistics(null);
            wallet.setDay_create(CalendarUtil.newInstance().getCurrentDate());
            ArrayList<Periodic> periodics = new ArrayList();
            for (int i = 1; i < totalItem; i++) {

                if (CreateWallet.newInstance().getItems().get(i).getType() > 0) {
                    periodics.add(new Periodic(CreateWallet.newInstance().getItems().get(i).getType(),
                            CreateWallet.newInstance().getItems().get(i).getMoney_event(),
                            CreateWallet.newInstance().getItems().get(i).getPeriod(),
                            CreateWallet.newInstance().getItems().get(i).getPeriod_day(),
                            CreateWallet.newInstance().getItems().get(i).getPeriod_month(),
                            CreateWallet.newInstance().getItems().get(i).getDescription()));
                } else {
                    Toast.makeText(this, getResources().getString(R.string.message_complete_input_create_wallet), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            wallet.setPeriodics(periodics);
            wallet.createWallet(this, wallet);
            finish();
        } else {

            Toast.makeText(this, getResources().getString(R.string.message_complete_input_create_wallet), Toast.LENGTH_SHORT).show();
        }
    }
}