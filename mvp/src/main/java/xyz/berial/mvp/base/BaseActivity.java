package xyz.berial.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.berial.mvp.RxLifecycle;

import static xyz.berial.mvp.Utils.checkNotNull;

/**
 * Created by Berial on 16/5/27.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements RxLifecycle.Impl {

    private RxLifecycle mLifecycle = new RxLifecycle();

    protected P mPresenter;

    abstract protected P bindPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = checkNotNull(bindPresenter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycle.onDestroy();
    }

    @Override
    public RxLifecycle bindLifecycle() {
        return mLifecycle;
    }
}
