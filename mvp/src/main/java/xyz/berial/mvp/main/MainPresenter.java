package xyz.berial.mvp.main;

import java.util.List;

import rx.functions.Action1;
import xyz.berial.mvp.RxThreadUtils;
import xyz.berial.mvp.data.MainDataSource;

/**
 * Created by Berial on 16/5/27.
 */
public class MainPresenter extends MainContract.Presenter {

    protected MainPresenter(MainContract.View view, MainDataSource dataSource) {
        super(view, dataSource);
    }

    @Override
    protected void loadStringList() {
        mDataSource.getStringList()
                .compose(RxThreadUtils.<List<String>>convert())
                .compose(this.<List<String>>bindLifecycle())
                .subscribe(new Action1<List<String>>() {

                    @Override
                    public void call(List<String> strings) {
                        mView.showStringList(strings);
                    }
                });
    }

}
