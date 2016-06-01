package xyz.berial.mvp.data;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * Created by Berial on 16/5/27.
 */
public class MainRepository implements MainDataSource {

    @Override
    public Observable<List<String>> getStringList() {
        String[] arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        return Observable.just(Arrays.asList(arr));
    }
}
