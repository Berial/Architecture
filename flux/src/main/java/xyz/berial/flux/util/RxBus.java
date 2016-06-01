package xyz.berial.flux.util;

import rx.Observable;
import rx.subjects.ReplaySubject;
import rx.subjects.SerializedSubject;

/**
 * RxBus
 * Created by Berial on 16/5/21.
 */
public class RxBus {

    private final SerializedSubject<Object, Object> bus =
            new SerializedSubject<>(ReplaySubject.create());

    private static RxBus instance;

    private RxBus() {}

    private static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public static void post(Object... objects) {
        if (objects == null) return;
        for (Object o : objects) {
            getInstance().bus.onNext(o);
        }
    }

    public static <T> Observable<T> get(Class<T> eventType) {
        return getInstance().bus.ofType(eventType);
    }
}
