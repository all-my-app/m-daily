package me.leduyhung.mdaily.ui.wallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.module_view.currency.Currency;
import me.leduyhung.mdaily.module.module_view.event.EventWallet;
import me.leduyhung.mdaily.module.module_view.groupwallet.GroupWallet;
import me.leduyhung.mdaily.module.module_view.period.Period;
import me.leduyhung.mdaily.module.module_view.period_day.PeriodDay;
import me.leduyhung.mdaily.module.module_view.period_month.PeriodMonth;

public class CreatePaidWalletActivity extends AppCompatActivity implements View.OnClickListener{

    private int idWallet;
    private Currency currency;
    private EventWallet eventWallet;

    private ImageView iLeft, iRight;
    private TextView tTitle;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_paid_wallet);
        idWallet = getIntent().getIntExtra(Constant.ListWallet.KEY_INTENT_ID_WALLET, -1);
        initView();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_left:
                finish();
                break;
        }
    }

    private void initView() {

        iLeft = findViewById(R.id.img_left);
        iRight = findViewById(R.id.img_right);
        tTitle = findViewById(R.id.txt_title);
        recycler = findViewById(R.id.recycler);
        configActionBar();
    }

    private void configActionBar() {
        tTitle.setText(getResources().getString(R.string.create_paid_wallet_title));
        iLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_back));
        iRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_white));
        iLeft.setOnClickListener(this);
        iRight.setOnClickListener(this);
        initDataCombo();
    }

    private void initDataCombo() {

        currency = new Currency(this);
        eventWallet = new EventWallet(this);
    }
}