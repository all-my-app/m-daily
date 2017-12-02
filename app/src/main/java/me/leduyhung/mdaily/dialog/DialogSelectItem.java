package me.leduyhung.mdaily.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.leduyhung.mdaily.R;
import me.leduyhung.mdaily.module.module_view.currency.CurrencyItem;
import me.leduyhung.mdaily.module.module_view.event.EventItem;
import me.leduyhung.mdaily.module.module_view.groupwallet.GroupItem;
import me.leduyhung.mdaily.module.module_view.period.PeriodItem;
import me.leduyhung.mdaily.module.module_view.period_day.PeriodDayItem;
import me.leduyhung.mdaily.module.module_view.period_month.PeriodMonthItem;

/**
 * Created by hungleduy on 11/9/17.
 */

public class DialogSelectItem<T> extends Dialog {

    private Context mContext;
    private ArrayList<T> arrData;

    private RecyclerView recyclerView;
    private SelectItemAdapter adap;

    private DialogSelectItemListener listener;

    public DialogSelectItem(Context context) {
        super(context);
        this.mContext = context;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_item);
        recyclerView = this.findViewById(R.id.recycler_dialog_select_item);
        configRecycler();
        setCancelable(true);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void showDialog(ArrayList<T> arrData, boolean fragmentIsDetach, DialogSelectItemListener listener) {

        if (!isShowing() && !((Activity) mContext).isFinishing() && !fragmentIsDetach) {

            this.listener = listener;
            this.arrData = arrData;
            if (adap != null)
                adap.notifyDataSetChanged();
            show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }

    private void configRecycler() {

        adap = new SelectItemAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adap);
        adap.notifyDataSetChanged();
    }

    private class SelectItemAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemView(LayoutInflater.from(mContext).inflate(R.layout.item_dialog_select_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            if (arrData.get(position) instanceof GroupItem) {
                ((ItemView) holder).tItem.setText(((GroupItem) arrData.get(position)).getName());
                ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onSelectItem(((GroupItem) arrData.get(position)).getId(), ((GroupItem) arrData.get(position)).getName());
                        dismiss();
                    }
                });
            } else if (arrData.get(position) instanceof EventItem) {
                ((ItemView) holder).tItem.setText(((EventItem) arrData.get(position)).getName());
                ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onSelectItem(((EventItem) arrData.get(position)).getId(), ((EventItem) arrData.get(position)).getName());
                        dismiss();
                    }
                });
            } else if (arrData.get(position) instanceof CurrencyItem) {

                ((ItemView) holder).tItem.setText(((CurrencyItem) arrData.get(position)).getName());
                ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onSelectItem(((CurrencyItem) arrData.get(position)).getId(), ((CurrencyItem) arrData.get(position)).getName());
                        dismiss();
                    }
                });
            } else if (arrData.get(position) instanceof PeriodItem) {

                ((ItemView) holder).tItem.setText(((PeriodItem) arrData.get(position)).getName());
                ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onSelectItem(((PeriodItem) arrData.get(position)).getId(), ((PeriodItem) arrData.get(position)).getName());
                        dismiss();
                    }
                });
            } else if (arrData.get(position) instanceof PeriodDayItem) {

                ((ItemView) holder).tItem.setText(((PeriodDayItem) arrData.get(position)).getName());
                ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onSelectItem(((PeriodDayItem) arrData.get(position)).getId(), ((PeriodDayItem) arrData.get(position)).getName());
                        dismiss();
                    }
                });
            } else {

                ((ItemView) holder).tItem.setText(((PeriodMonthItem) arrData.get(position)).getName());
                ((ItemView) holder).lItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.onSelectItem(((PeriodMonthItem) arrData.get(position)).getId(), ((PeriodMonthItem) arrData.get(position)).getName());
                        dismiss();
                    }
                });
            }
        }


        @Override
        public int getItemCount() {
            return arrData.size();
        }

        class ItemView extends RecyclerView.ViewHolder {

            private LinearLayout lItem;
            private TextView tItem;

            public ItemView(View itemView) {
                super(itemView);

                lItem = itemView.findViewById(R.id.linear_item);
                tItem = itemView.findViewById(R.id.txt_item);
            }
        }
    }

    public interface DialogSelectItemListener {
        void onSelectItem(int id, String name);
    }
}