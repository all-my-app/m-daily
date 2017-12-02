package me.leduyhung.mdaily.module.module_view.event;

/**
 * Created by hungleduy on 11/14/17.
 */

public class EventItem {

    private int id;
    private String name;

    public EventItem() {
    }

    public EventItem(int id, String name) {
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