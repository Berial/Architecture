package xyz.berial.flux.flux;

import java.util.ArrayList;
import java.util.Arrays;

import xyz.berial.flux.action.StringListAction;

/**
 * ActionsCreator
 * Created by Berial on 16/5/31.
 */
public class ActionsCreator {

    private static ActionsCreator instance;
    private final Dispatcher dispatcher;

    private ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            synchronized (ActionsCreator.class) {
                if (instance == null) {
                    instance = new ActionsCreator(dispatcher);
                }
            }
        }
        return instance;
    }

    public void loadStringList() {
        // 做io操作

        String[] stringArray = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(stringArray));

        dispatcher.dispatch(new StringListAction(
                StringListAction.ACTION_LOAD_STRING_LIST,
                stringList
        ));
    }
}
