package me.leduyhung.mdaily.module.module_view.groupwallet;

/**
 * Created by hungleduy on 11/13/17.
 */

public class GroupItem {

    private int id;
    private String name;

    public GroupItem() {
    }

    public GroupItem(int id, String name) {
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