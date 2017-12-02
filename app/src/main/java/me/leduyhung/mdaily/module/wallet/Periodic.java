package me.leduyhung.mdaily.module.wallet;

/**
 * Created by hungleduy on 11/9/17.
 */

public class Periodic {

    private int type;
    private long money_event;
    private int period;
    private int period_day;
    private int period_month;
    private String description;

    public Periodic(int type, long money_event, int period, int period_day, int period_month, String description) {
        this.type = type;
        this.money_event = money_event;
        this.period = period;
        this.period_day = period_day;
        this.period_month = period_month;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMoney_event() {
        return money_event;
    }

    public void setMoney_event(long money_event) {
        this.money_event = money_event;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod_day() {
        return period_day;
    }

    public void setPeriod_day(int period_day) {
        this.period_day = period_day;
    }

    public int getPeriod_month() {
        return period_month;
    }

    public void setPeriod_month(int period_month) {
        this.period_month = period_month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}