package me.leduyhung.mdaily.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by hungleduy on 12/15/17.
 */

public class NumberConvert {

    private static NumberConvert numberConvert;

    public static NumberConvert newInstance() {

        if (numberConvert == null)
            numberConvert = new NumberConvert();
        return numberConvert;
    }

    public String convertNumberCurrency(long money, String currency) {

        NumberFormat formatter = new DecimalFormat("#,###");
        String moneyConvert = formatter.format(money);
        moneyConvert = moneyConvert.replace(".",",");
        return moneyConvert + " " + currency;
    }
}