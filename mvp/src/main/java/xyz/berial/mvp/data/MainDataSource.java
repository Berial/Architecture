package xyz.berial.mvp.data;

import java.util.List;

import rx.Observable;
import xyz.berial.mvp.base.BaseDataSource;

/**
 * Created by Berial on 16/5/28.
 */
public interface MainDataSource extends BaseDataSource {
    Observable<List<String>> getStringList();
}
