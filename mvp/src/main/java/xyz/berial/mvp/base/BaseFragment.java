package xyz.berial.mvp.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import xyz.berial.mvp.RxLifecycle;

import static xyz.berial.mvp.Utils.checkNotNull;

/**
 * Created by Berial on 16/5/27.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements RxLifecycle.Impl {

    private RxLifecycle mLifecycle = new RxLifecycle();

    protected P mPresenter;

    abstract protected P bindPresenter();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = checkNotNull(bindPresenter());
    }

    @Override
    public RxLifecycle bindLifecycle() {
        return mLifecycle;
    }

    @Override
    public void onDestroyView() {
        mLifecycle.onDestroy();
        super.onDestroyView();
    }
}
