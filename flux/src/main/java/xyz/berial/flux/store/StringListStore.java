package xyz.berial.flux.store;

import java.util.List;

import xyz.berial.flux.action.Action;
import xyz.berial.flux.action.StringListAction;

/**
 * Created by Berial on 16/5/31.
 */
public final class StringListStore extends Store {

    private static StringListStore instance;

    private List<String> stringList;

    public static StringListStore getInstance() {
        if (instance == null) {
            synchronized (StringListStore.class) {
                if (instance == null) {
                    instance = new StringListStore();
                }
            }
        }
        return instance;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public void onAction(Action action) {
        switch (action.getType()) {
            case StringListAction.ACTION_LOAD_STRING_LIST:
                stringList = ((StringListAction) action).getData();
                break;
            default:
        }
        post(action.getType());
    }

    @Override
    public Event changeEvent(String type) {
        switch (type) {
            case StringListAction.ACTION_LOAD_STRING_LIST:
                return new LoadFinishEvent();
            default:
                return new Event();
        }
    }

    public final class LoadFinishEvent extends Event {}
}
