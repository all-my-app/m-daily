package me.leduyhung.mdaily.ui.wallet;

import android.app.Activity;
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
import java.util.List;

import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.db.AppDatabase;
import me.leduyhung.mdaily.module.wallet.Wallet;
import me.leduyhung.mdaily.observer.ObserverTag;
import me.leduyhung.mdaily.observer.UiObserver;
import me.leduyhung.mdaily.ui.wallet.adapter.ListWalletAdapter;

/**
 * Created by hungleduy on 11/4/17.
 */

public class ListWalletFragment extends Fragment implements View.OnClickListener, Observer<List<Wallet>> {

    private Context mContext;
    private View v;
    private FloatingActionButton bAdd;
    private ImageView iMenu;
    private TextView tTitle;
    private RecyclerView recycler;
    private ListWalletAdapter adap;

    private ArrayList<Wallet> arrData;
    private boolean needRemoveNoItem, firstLoad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstLoad = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        iMenu.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_list_wallet, container, false);
        iMenu = v.findViewById(R.id.img_left);
        tTitle = v.findViewById(R.id.txt_title);
        bAdd = v.findViewById(R.id.btn_add);
        recycler = v.findViewById(R.id.recycler);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bAdd.setOnClickListener(this);
        tTitle.setText(mContext.getResources().getString(R.string.wallet_title_actionbar));
        configRecycler();
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        AppDatabase.newInstance(mContext).walletDao().getAllDataWallet().removeObserver(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.img_left:

                UiObserver.newInstance().notify(ObserverTag.TAG_ICON_LEFT_ACTION_BAR_CLICK);
                break;
            case R.id.btn_add:
                Intent intent = new Intent(mContext, CreateWalletActivity.class);
                intent.putExtra(Constant.ListWallet.KEY_INTENT_TOTAL_WALLET, arrData.size());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onChanged(@Nullable final List<Wallet> wallets) {

        Logg.error(getClass(), "wallet total: " + wallets.size());
        if (wallets.size() > 0) {
            arrData.clear();
            arrData.addAll(wallets);
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (needRemoveNoItem) {
                        adap.notifyItemChanged(0);
                    } else {
                        if (!firstLoad) {
                            adap.notifyItemInserted(wallets.size() - 1);
                            recycler.smoothScrollToPosition(wallets.size() - 1);
                        } else {
                            adap.notifyDataSetChanged();
                            firstLoad = false;
                        }
                    }
                    needRemoveNoItem = false;
                }
            });
        } else {

            needRemoveNoItem = true;
        }
    }

    private void configRecycler() {

        arrData = new ArrayList();
        adap = new ListWalletAdapter(mContext, arrData);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        manager.setAutoMeasureEnabled(false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adap);
        AppDatabase.newInstance(mContext).walletDao().getAllDataWallet().observeForever(this);
    }
}