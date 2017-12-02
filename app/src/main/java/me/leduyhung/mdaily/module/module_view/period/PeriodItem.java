package me.leduyhung.mdaily.module.module_view.period;

/**
 * Created by hungleduy on 11/15/17.
 */

public class PeriodItem {

    private int id;
    private String name;

    public PeriodItem(int id, String name) {
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