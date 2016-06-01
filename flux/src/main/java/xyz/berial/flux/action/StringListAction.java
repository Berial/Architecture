package xyz.berial.flux.action;

import java.util.List;

/**
 * StringListAction
 * Created by Berial on 16/5/31.
 */
public class StringListAction extends Action<List<String>> {

    public static final String ACTION_LOAD_STRING_LIST = "load_string_list";

    public StringListAction(String type, List<String> data) {
        super(type, data);
    }
}
