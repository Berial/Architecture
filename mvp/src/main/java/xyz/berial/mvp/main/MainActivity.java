package xyz.berial.mvp.main;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import xyz.berial.mvp.R;
import xyz.berial.mvp.base.BaseActivity;
import xyz.berial.mvp.data.MainRepository;

import static xyz.berial.mvp.Utils.checkNotNull;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    private static final String TAG = "MainActivity";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
    }

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenter(this, new MainRepository());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadStringList();
    }

    @Override
    public void showStringList(List<String> list) {
        checkNotNull(mTextView);
        checkNotNull(list);
        mTextView.setText(list.toString());
    }
}
