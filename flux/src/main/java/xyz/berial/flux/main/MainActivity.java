package xyz.berial.flux.main;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import xyz.berial.flux.R;
import xyz.berial.flux.base.BaseActivity;
import xyz.berial.flux.store.StringListStore;
import xyz.berial.flux.util.RxBus;
import xyz.berial.flux.util.RxLifecycle;

import static xyz.berial.flux.util.Utils.checkNotNull;

public class MainActivity extends BaseActivity<StringListStore> {

    private StringListStore mStore;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        subscribe();
    }

    @Override
    protected StringListStore bindStore() {
        mStore = StringListStore.getInstance();
        return mStore;
    }

    private void subscribe() {
        RxBus.get(StringListStore.LoadFinishEvent.class)
                .compose(RxLifecycle.bindLifecycle(this))
                .flatMap(new Func1<Object, Observable<List<String>>>() {
                    @Override
                    public Observable<List<String>> call(Object o) {
                        return Observable.just(mStore.getStringList());
                    }
                })
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        showStringList(strings);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mCreator.loadStringList();
    }

    public void showStringList(List<String> list) {
        checkNotNull(mTextView);
        checkNotNull(list);
        mTextView.setText(list.toString());
    }

    @Override
    public RxLifecycle bindLifecycle() {
        return new RxLifecycle();
    }
}
