package me.leduyhung.mdaily.ui.home;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.user.User;
import me.leduyhung.mdaily.observer.UiObserver;
import me.leduyhung.mdaily.observer.listener.ActionBarEvent;
import me.leduyhung.mdaily.observer.listener.UserEvent;
import me.leduyhung.mdaily.ui.login.LoginActivity;
import me.leduyhung.mdaily.ui.wallet.ListWalletFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ListWalletFragment listWalletFragment;

    private LinearLayout lWallet, lSchedule, lExchange, lLogout;

    private DrawerLayout drawerLayout;

    private ActionBarEvent actionBarEvent = new ActionBarEvent() {
        @Override
        public void onImgLeftClick() {

            if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {

                drawerLayout.openDrawer(Gravity.LEFT);
            }
        }

        @Override
        public void onImgRightClick() {

        }
    };

    private UserEvent.UserLogout userLogout = new UserEvent.UserLogout() {
        @Override
        public void onLogoutComplete() {

            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        openListWallet();
    }

    @Override
    protected void onResume() {
        super.onResume();

        UiObserver.newInstance().listener(actionBarEvent);
        UiObserver.newInstance().listener(userLogout);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.item_wallet:
                openListWallet();
                break;
            case R.id.item_schedule:
                openSchedule();
                break;
            case R.id.item_exchange:
                openExcange();
                break;
            case R.id.item_logout:
                User.newInstance(this).logout();
                break;
        }
    }

    private void initView() {

        drawerLayout = findViewById(R.id.drawer_layout);
        lWallet = findViewById(R.id.item_wallet);
        lSchedule = findViewById(R.id.item_schedule);
        lExchange = findViewById(R.id.item_exchange);
        lLogout = findViewById(R.id.item_logout);
        lWallet.setOnClickListener(this);
        lSchedule.setOnClickListener(this);
        lExchange.setOnClickListener(this);
        lLogout.setOnClickListener(this);
    }

    private void openListWallet() {

        if (listWalletFragment == null) {

            listWalletFragment = new ListWalletFragment();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction().replace(R.id.frame_content, listWalletFragment);
            transaction.commit();
        }
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void openSchedule() {

        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    private void openExcange() {

        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}