package xyz.berial.mvp.main;

import java.util.List;

import xyz.berial.mvp.base.BasePresenter;
import xyz.berial.mvp.base.BaseView;
import xyz.berial.mvp.data.MainDataSource;

/**
 * Created by Berial on 16/5/27.
 */
public interface MainContract {

    interface View extends BaseView {
        void showStringList(List<String> list);
    }

    abstract class Presenter extends BasePresenter<View, MainDataSource> {

        protected Presenter(View view, MainDataSource dataSource) {
            super(view, dataSource);
        }

        abstract protected void loadStringList();
    }
}
