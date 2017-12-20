package me.leduyhung.mdaily.module.module_view.createpaidwallet;

import java.util.ArrayList;

/**
 * Created by hungleduy on 12/19/17.
 */

public class CreatePaidWallet {

    private ArrayList<CreatePaidWalletItem> items;
    private static CreatePaidWallet createPaidWallet;

    public static CreatePaidWallet newInstance() {

        if (createPaidWallet == null)
            createPaidWallet = new CreatePaidWallet();
        return createPaidWallet;
    }

    public ArrayList<CreatePaidWalletItem> getItems() {

        if (items == null)
            makeItems();
        return items;
    }

    public void addNewItem() {

        items.add(new CreatePaidWalletItem(0, 0, 0, ""));
    }

    private void makeItems() {

        items = new ArrayList();
        items.add(new CreatePaidWalletItem(0, 0, 0, ""));
    }

    public void removeItemByPosition(int position) {

        if (position < items.size())
            items.remove(position);
    }

    public void destroy() {

        items = null;
        createPaidWallet = null;
    }
}