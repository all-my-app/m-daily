package me.leduyhung.mdaily.module.module_view.createwallet;

/**
 * Created by hungleduy on 11/14/17.
 */

public class CreateItem {

    private String name;
    private int group;
    private long money_in_wallet;
    private int currency;

    private int type;
    private long money_event;
    private int period;
    private int period_day;
    private int period_month;
    private String description;

    public CreateItem(String name, int group, long money_in_wallet, int currency, String description) {
        this.name = name;
        this.group = group;
        this.money_in_wallet = money_in_wallet;
        this.currency = currency;
        this.description = description;
    }

    public CreateItem(int type, long money_event, String description) {
        this.type = type;
        this.money_event = money_event;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public long getMoney_in_wallet() {
        return money_in_wallet;
    }

    public void setMoney_in_wallet(long money_in_wallet) {
        this.money_in_wallet = money_in_wallet;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeriod_month() {
        return period_month;
    }

    public void setPeriod_month(int period_month) {
        this.period_month = period_month;
    }
}