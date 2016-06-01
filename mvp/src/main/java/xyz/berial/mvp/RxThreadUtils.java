package xyz.berial.mvp;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Berial on 16/5/26.
 */
public class RxThreadUtils<T> {

    private static RxThreadUtils sInstance;

    private final Observable.Transformer<T, T> schedulersTransformer = new Observable.Transformer<T, T>() {

        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private final Observable.Transformer<T, T> main = new Observable.Transformer<T, T>() {

        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private RxThreadUtils() {}

    private static RxThreadUtils newInstance() {
        if (sInstance == null) {
            synchronized (RxThreadUtils.class) {
                if (sInstance == null) {
                    sInstance = new RxThreadUtils();
                }
            }
        }
        return sInstance;
    }

    public static <T> Observable.Transformer<T, T> convert() {
        return newInstance().schedulersTransformer;
    }

    public static <T> Observable.Transformer<T, T> main() {
        return newInstance().main;
    }
}
