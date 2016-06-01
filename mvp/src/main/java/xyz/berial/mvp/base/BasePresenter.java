package xyz.berial.mvp.base;

import android.support.annotation.NonNull;

import rx.Observable;
import xyz.berial.mvp.RxLifecycle;

import static xyz.berial.mvp.Utils.checkNotNull;

/**
 * Created by Berial on 16/5/27.
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseDataSource> {

    protected V mView;
    protected M mDataSource;

    protected BasePresenter(@NonNull V view, @NonNull M dataSource) {
        mView = checkNotNull(view);
        mDataSource = checkNotNull(dataSource);
    }

    protected <T> Observable.Transformer<T, T> bindLifecycle() {
        return RxLifecycle.bindLifecycle(mView);
    }
}
