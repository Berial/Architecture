package xyz.berial.flux.util;

/**
 * Created by Berial on 16/5/27.
 */
public final class Utils {

    public static <T> T checkNotNull(T t) {
        if (t == null) throw new NullPointerException();
        return t;
    }
}
