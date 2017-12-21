package me.leduyhung.mdaily.ui.wallet;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leduyhung.loglibrary.Logg;

import java.util.ArrayList;

import leduyhung.view.myspinner.MySpinnerView;
import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.db.AppDatabase;
import me.leduyhung.mdaily.module.wallet.Bill;
import me.leduyhung.mdaily.module.wallet.Statistical;
import me.leduyhung.mdaily.module.wallet.Wallet;
import me.leduyhung.mdaily.observer.ObserverTag;
import me.leduyhung.mdaily.observer.UiObserver;
import me.leduyhung.mdaily.ui.wallet.adapter.ListPaidWalletAdapter;

/**
 * Created by hungleduy on 11/14/17.
 */

public class ListPaidWalletFragment extends Fragment implements View.OnClickListener, Observer<Wallet> {

    private Context mContext;
    private View v;
    private ImageView iLeft, iRight;
    private TextView tTitle;
    private MySpinnerView spiner;
    private FloatingActionButton bAdd;
    private RecyclerView recycler;
    private ListPaidWalletAdapter adap;

    private ArrayList<Statistical> arrData;
    private int idWallet;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idWallet = getArguments().getInt(Constant.ListWallet.KEY_BUNDLE_ID_WALLET);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_list_paid_wallet, container, false);
        iLeft = v.findViewById(R.id.img_left);
        iRight = v.findViewById(R.id.img_right);
        tTitle = v.findViewById(R.id.txt_title);
        spiner = v.findViewById(R.id.spinner_actionbar);
        bAdd = v.findViewById(R.id.btn_add);
        recycler = v.findViewById(R.id.recycler);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tTitle.setText(mContext.getResources().getString(R.string.wallet_paid_title_actionbar));
        iLeft.setOnClickListener(this);
        iRight.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        configRecycler();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        AppDatabase.newInstance(mContext).walletDao().getWalletById(idWallet).removeObserver(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_left:
                UiObserver.newInstance().notify(ObserverTag.TAG_ICON_LEFT_ACTION_BAR_CLICK);
                break;
            case R.id.img_right:
                UiObserver.newInstance().notify(ObserverTag.TAG_ICON_RIGHT_ACTION_BAR_CLICK);
                break;
            case R.id.btn_add:
                Intent i = new Intent(mContext, CreatePaidWalletActivity.class);
                i.putExtra(Constant.ListWallet.KEY_INTENT_ID_WALLET, idWallet);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onChanged(@Nullable Wallet wallet) {

        Logg.error(getClass(), wallet.getName() + "  --->");
        if (wallet.getStatistics() != null) {
            arrData.clear();
            arrData.addAll(wallet.getStatistics());
            if (arrData.size() > 0) {
                adap.updateListPaid(arrData.get(0).getBills());
            }
        }
    }

    private void configRecycler() {

        arrData = new ArrayList<>();
        adap = new ListPaidWalletAdapter(mContext, new ArrayList<Bill>());
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adap);
        AppDatabase.newInstance(mContext).walletDao().getWalletById(idWallet).observeForever(this);
    }
}