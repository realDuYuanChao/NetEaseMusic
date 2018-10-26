package shellhub.github.neteasemusic.view.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.NavProfile;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presentation.MainPresenter;
import shellhub.github.neteasemusic.presentation.impl.MainPresenterImpl;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.view.MainView;

public class MainActivity extends BaseApp
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private View navHeader;
    private ImageView ivAvatar;
    private TextView tvNickname;
    private TextView tvLevel;

    private final String TAG = MainActivity.class.getSimpleName();
    private Bundle receiveData;

    private MainPresenter mainPresenter;

    @Inject
    public NetEaseMusicService mNetEaseMusicService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setUpNavHeader();
        setUpMVP();

        receiveData = getIntent().getExtras();
        mainPresenter.update(receiveData);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    /**
     * px转换dip
     */
    public int px2dip(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setUpNavHeader() {
        navHeader = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ivAvatar = navHeader.findViewById(R.id.iv_avatar);
        tvNickname = navHeader.findViewById(R.id.tv_nickname);
        tvLevel = navHeader.findViewById(R.id.tv_level);
    }

    @Override
    public void setUpMVP() {
        mainPresenter = new MainPresenterImpl(mNetEaseMusicService, this);
    }

    @Override
    public void updateNavProfile(final NavProfile navProfile) {
        Glide.with(this).load(navProfile.getAvatarUrl()).into(ivAvatar);
        tvNickname.setText(navProfile.getNickname());

        Glide.with(this).load(navProfile.getBackgroundUrl()).apply(new RequestOptions().centerCrop()
                .fitCenter()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    navHeader.setBackground(resource);
                    //background bug TODO
                }
            }
        });

    }

    @Override
    public void updateDetail(DetailResponse detailResponse) {
        LogUtils.d(TAG, detailResponse);
        tvLevel.append(detailResponse.getLevel() + "");
    }
}
