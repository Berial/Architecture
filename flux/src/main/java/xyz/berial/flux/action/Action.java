package xyz.berial.flux.action;

/**
 * Action
 * Created by Berial on 16/5/31.
 */
public class Action<T> {

    private final String type;
    private final T data;

    public Action(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
