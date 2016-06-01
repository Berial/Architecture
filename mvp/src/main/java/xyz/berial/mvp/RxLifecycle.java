package xyz.berial.mvp;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;
import xyz.berial.mvp.base.BaseView;

/**
 * Created by Berial on 16/5/27.
 */
public class RxLifecycle {

    public enum Event {
        DESTROY
    }

    private final BehaviorSubject<Event> eventBehavior = BehaviorSubject.create();

    public void onDestroy() {
        eventBehavior.onNext(Event.DESTROY);
    }

    public static <T> Observable.Transformer<T, T> bindLifecycle(@NonNull BaseView view) {
        return new CheckLifeCycleTransformer<>(view.bindLifecycle().eventBehavior);
    }

    public interface Impl {
        RxLifecycle bindLifecycle();
    }

    public static class CheckLifeCycleTransformer<T> implements Observable.Transformer<T, T> {

        private BehaviorSubject<Event> mEventBehavior;

        public CheckLifeCycleTransformer(BehaviorSubject<Event> eventBehavior) {
            mEventBehavior = eventBehavior;
        }

        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.takeUntil(mEventBehavior.skipWhile(new Func1<Event, Boolean>() {

                @Override
                public Boolean call(Event event) {
                    return event != Event.DESTROY;
                }
            }));
        }
    }
}