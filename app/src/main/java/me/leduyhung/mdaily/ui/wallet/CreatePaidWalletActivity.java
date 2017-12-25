package me.leduyhung.mdaily.ui.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leduyhung.loglibrary.Logg;

import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.dialog.DialogSelectItem;
import me.leduyhung.mdaily.module.module_view.createpaidwallet.CreatePaidWallet;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.event.EventWallet;
import me.leduyhung.mdaily.ui.wallet.adapter.CreatePaidWalletAdapter;

public class CreatePaidWalletActivity extends AppCompatActivity implements View.OnClickListener, CreatePaidWalletAdapter.CreatePaidWalletAdapterListener{

    private int idWallet;
    private Currency currency;
    private EventWallet eventWallet;

    private ImageView iLeft, iRight;
    private TextView tTitle;
    private RecyclerView recycler;
    private DialogSelectItem dialogSelectItem;

    private CreatePaidWalletAdapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_paid_wallet);
        idWallet = getIntent().getIntExtra(Constant.ListWallet.KEY_INTENT_ID_WALLET, -1);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        CreatePaidWallet.newInstance().destroy();
        dialogSelectItem.dismiss();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_left:
                finish();
                break;
        }
    }

    @Override
    public void onComboboxClick(int type_combobox, int position) {

        switch (type_combobox) {
            case CreatePaidWalletAdapter.TYPE_COMBOBOX_TYPE:
                showDialogSelectType(position);
                break;
            case CreatePaidWalletAdapter.TYPE_COMBOBOX_CURRENCY:
                showDialogSelectCurrency(position);
                break;
        }
    }

    @Override
    public void onAddItem(int totalItem) {

        recycler.smoothScrollToPosition(totalItem - 1);
    }

    @Override
    public void onRemoveItem(int position, int totalItem) {

    }

    private void initView() {

        iLeft = findViewById(R.id.img_left);
        iRight = findViewById(R.id.img_right);
        tTitle = findViewById(R.id.txt_title);
        recycler = findViewById(R.id.recycler);
        dialogSelectItem = new DialogSelectItem(this);
        configActionBar();
        configRecycler();
    }

    private void configActionBar() {
        tTitle.setText(getResources().getString(R.string.create_paid_wallet_title));
        iLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        iRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_white));
        iLeft.setOnClickListener(this);
        iRight.setOnClickListener(this);
        initDataCombo();
    }

    private void configRecycler() {

        adap = new CreatePaidWalletAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adap);
        adap.setAdapterClickListener(this);
    }

    private void initDataCombo() {

        currency = new Currency(this);
        eventWallet = new EventWallet(this);
    }

    private void showDialogSelectType(final int position) {

        dialogSelectItem.showDialog(eventWallet.getItem(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreatePaidWalletActivity.this.getClass(), "showDialogSelectType -> id =" + id + " -- name=" + name);
                CreatePaidWallet.newInstance().getItems().get(position).setType(id);
                adap.notifyItemChanged(position);
            }
        });
    }

    private void showDialogSelectCurrency(final int position) {

        dialogSelectItem.showDialog(currency.getItems(), false, new DialogSelectItem.DialogSelectItemListener() {
            @Override
            public void onSelectItem(int id, String name) {

                Logg.error(CreatePaidWalletActivity.this.getClass(), "showDialogSelectCurrency -> id =" + id + " -- name=" + name);
                CreatePaidWallet.newInstance().getItems().get(position).setCurrency(id);
                adap.notifyItemChanged(position);
            }
        });
    }
}