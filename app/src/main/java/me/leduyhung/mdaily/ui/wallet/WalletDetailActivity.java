package me.leduyhung.mdaily.ui.wallet;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.leduyhung.loglibrary.Logg;

import me.leduyhung.mdaily.Constant;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.observer.UiObserver;
import me.leduyhung.mdaily.observer.listener.ActionBarEvent;

public class WalletDetailActivity extends AppCompatActivity implements ActionBarEvent {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private ListPaidWalletFragment listPaidWalletFragment;
    private WalletInforFragment walletInforFragment;

    private int idWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);
        openListWallet();

        idWallet = getIntent().getIntExtra(Constant.ListWallet.KEY_INTENT_ID_WALLET, -1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        UiObserver.newInstance().listener(this);
    }

    @Override
    public void onImgLeftClick() {

        finish();
    }

    @Override
    public void onImgRightClick() {

        flipFragment();
    }

    private void openListWallet() {

        if (listPaidWalletFragment == null) {

            Bundle bundle = new Bundle();
            bundle.putInt(Constant.ListWallet.KEY_BUNDLE_ID_WALLET, idWallet);
            listPaidWalletFragment = new ListPaidWalletFragment();
            listPaidWalletFragment.setArguments(bundle);
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction().replace(R.id.frame_content, listPaidWalletFragment);
            transaction.commit();
        }
    }

    private void flipFragment() {

        if (listPaidWalletFragment.isVisible()) {

            if (walletInforFragment == null)
                walletInforFragment = new WalletInforFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.ListWallet.KEY_BUNDLE_ID_WALLET, idWallet);
            walletInforFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_flip_right_in,
                    R.anim.fragment_flip_right_out,
                    R.anim.fragment_flip_left_in,
                    R.anim.fragment_flip_left_out).replace(R.id.frame_content, walletInforFragment).addToBackStack(null).commit();
            return;
        } else {

            getSupportFragmentManager().popBackStack();
        }
    }
}