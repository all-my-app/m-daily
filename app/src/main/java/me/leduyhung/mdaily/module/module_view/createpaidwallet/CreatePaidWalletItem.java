package me.leduyhung.mdaily.module.module_view.createpaidwallet;

/**
 * Created by hungleduy on 12/19/17.
 */

public class CreatePaidWalletItem {

    private int type;
    private int currency;
    private long money_event;
    private String description;

    public CreatePaidWalletItem(int type, int currency, long money_event, String description) {
        this.type = type;
        this.currency = currency;
        this.money_event = money_event;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public long getMoney_event() {
        return money_event;
    }

    public void setMoney_event(long money_event) {
        this.money_event = money_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
