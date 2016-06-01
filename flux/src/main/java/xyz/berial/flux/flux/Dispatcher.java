package xyz.berial.flux.flux;

import java.util.ArrayList;

import xyz.berial.flux.action.Action;
import xyz.berial.flux.store.Store;

/**
 * 分发者
 * Created by Berial on 16/5/31.
 */
public class Dispatcher {

    private static Dispatcher instance;
    private ArrayList<Store> stores = new ArrayList<>();

    private Dispatcher() {}

    public static Dispatcher getInstance() {
        if (instance == null) {
            synchronized (Dispatcher.class) {
                if (instance == null) {
                    instance = new Dispatcher();
                }
            }
        }
        return instance;
    }

    public void register(Store store) {
        if (!stores.contains(store)) {
            stores.add(store);
        }
    }

    public void unregister(Store store) {
        stores.remove(store);
    }

    public void dispatch(Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
