package shellhub.github.neteasemusic.ui.activities;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.LocalCategoryPagerAdapter;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.ArtistEvent;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.entities.SingleEvent;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.presenter.impl.LocalPresenterImpl;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.LocalView;

public class LocalActivity extends AppCompatActivity implements LocalView {

    private String TAG = TagUtils.getTag(this.getClass());
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
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    @Override
    public void loadArtist(List<Artist> artists) {
        LogUtils.d(TAG, artists);
        EventBus.getDefault().post(new ArtistEvent(artists));
    }
}
