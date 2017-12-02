package me.leduyhung.mdaily.module.module_view.createwallet;

import java.util.ArrayList;

/**
 * Created by hungleduy on 11/14/17.
 */

public class CreateWallet {

    private ArrayList<CreateItem> items;
    private static CreateWallet createWallet;

    public static CreateWallet newInstance() {

        if (createWallet == null) {
            createWallet = new CreateWallet();
        }

        return createWallet;
    }

    public CreateWallet() {
    }

    public CreateWallet(ArrayList<CreateItem> items) {
        this.items = items;
    }

    public ArrayList<CreateItem> getItems() {
        if (items == null) {
            makeItems();
        }
        return items;
    }

    public void addNewItem() {

        items.add(new CreateItem(0, 0, ""));
    }

    public void removeItemByPosition(int position) {

        if (position < items.size())
            items.remove(position);
    }

    private void makeItems() {

        items = new ArrayList();
        items.add(new CreateItem("", 0, 0, 0, ""));
        items.add(new CreateItem(0, 0,""));
    }

    public void destroy() {

        items = null;
        createWallet = null;
    }
}