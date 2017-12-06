package me.leduyhung.mdaily.ui.wallet;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import me.leduyhung.mdaily.R;

public class WalletDetailActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ListPaidWalletFragment listPaidWalletFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_detail);
        openListWallet();
    }

    private void openListWallet() {

        if (listPaidWalletFragment == null) {

            listPaidWalletFragment = new ListPaidWalletFragment();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction().replace(R.id.frame_content, listPaidWalletFragment);
            transaction.commit();
        }
    }
}