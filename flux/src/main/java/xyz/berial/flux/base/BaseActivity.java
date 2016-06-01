package xyz.berial.flux.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.berial.flux.flux.ActionsCreator;
import xyz.berial.flux.util.RxLifecycle;
import xyz.berial.flux.flux.Dispatcher;
import xyz.berial.flux.store.Store;

/**
 * Created by Berial on 16/5/31.
 */
public abstract class BaseActivity<S extends Store> extends AppCompatActivity implements RxLifecycle.Impl {

    private RxLifecycle lifecycle = new RxLifecycle();

    private Dispatcher mDispatcher;
    private S mStore;
    protected ActionsCreator mCreator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDispatcher = Dispatcher.getInstance();

        mStore = bindStore();
        mDispatcher.register(mStore);

        mCreator = ActionsCreator.getInstance(mDispatcher);
    }

    protected abstract S bindStore();

    @Override
    public RxLifecycle bindLifecycle() {
        return lifecycle;
    }

    @Override
    protected void onDestroy() {
        lifecycle.onDestroy();
        mDispatcher.unregister(mStore);
        super.onDestroy();
    }
}
