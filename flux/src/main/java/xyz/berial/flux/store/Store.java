package xyz.berial.flux.store;

import xyz.berial.flux.util.RxBus;
import xyz.berial.flux.action.Action;

/**
 * Store
 * Created by Berial on 16/5/31.
 */
public abstract class Store {

    protected Store() {
    }

    void post(String type) {
        RxBus.post(changeEvent(type));
    }

    public abstract Event changeEvent(String type);

    public abstract void onAction(Action action);

    public class Event {}
}
