package shellhub.github.neteasemusic.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.LocalCategoryPagerAdapter;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.entities.SingleEvent;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.presenter.impl.LocalPresenterImpl;
import shellhub.github.neteasemusic.ui.fragments.SingleFragment;
import shellhub.github.neteasemusic.view.LocalView;

public class LocalActivity extends AppCompatActivity implements LocalView {

    private String TAG = LocalActivity.class.getSimpleName();
    @BindView(R.id.tl_local_category)
    TabLayout tlLocalCategory;

    @BindView(R.id.vp_local_files)
    ViewPager vpLocalFiles;

    private LocalPresenter mLocalPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);
        vpLocalFiles.setAdapter(new LocalCategoryPagerAdapter(getSupportFragmentManager(), this));
        tlLocalCategory.setupWithViewPager(vpLocalFiles);
        setUpMVP();
    }

    @Override
    public void setUpMVP() {
        mLocalPresenter = new LocalPresenterImpl(this);
        mLocalPresenter.load();
    }

    @Override
    public void loadSingle(List<Single> singles) {
        LogUtils.d(TAG, singles);
        EventBus.getDefault().post(new SingleEvent(singles));
    }
}
