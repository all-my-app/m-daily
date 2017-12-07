package me.leduyhung.mdaily.module.wallet;

import java.util.Date;

/**
 * Created by hungleduy on 11/9/17.
 */

public class Bill {

    private Date payDate;
    private String description;
    private int oldMoney;
    private int changeMoney;

    public Bill() {
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(int oldMoney) {
        this.oldMoney = oldMoney;
    }

    public int getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(int changeMoney) {
        this.changeMoney = changeMoney;
    }
}