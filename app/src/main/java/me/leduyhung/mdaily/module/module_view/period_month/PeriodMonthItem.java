package me.leduyhung.mdaily.module.module_view.period_month;

/**
 * Created by hungleduy on 11/16/17.
 */

public class PeriodMonthItem {

    private int id;
    private String name;

    public PeriodMonthItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
