package me.leduyhung.mdaily.ui.wallet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import leduyhung.view.myspinner.MySpinnerView;
import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.observer.ObserverTag;
import me.leduyhung.mdaily.observer.UiObserver;

/**
 * Created by hungleduy on 11/14/17.
 */

public class WalletInforFragment extends Fragment implements View.OnClickListener{

    private Context mContext;
    private View v;
    private ImageView iLeft, iRight;
    private TextView tTitle;
    private MySpinnerView spiner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_wallet_infor, container, false);
        iLeft = v.findViewById(R.id.img_left);
        iRight = v.findViewById(R.id.img_right);
        tTitle = v.findViewById(R.id.txt_title);
        spiner = v.findViewById(R.id.spinner_actionbar);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tTitle.setText(mContext.getResources().getString(R.string.wallet_infor_title_actionbar));
        iLeft.setOnClickListener(this);
        iRight.setOnClickListener(this);
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
        }
    }
}