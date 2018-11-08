package shellhub.github.neteasemusic.ui.activities;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.MusicMenu;
import shellhub.github.neteasemusic.model.entities.MusicMenuIndexEvent;
import shellhub.github.neteasemusic.model.entities.NavProfile;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.MainPresenter;
import shellhub.github.neteasemusic.presenter.impl.MainPresenterImpl;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.ui.fragments.MainFragment;
import shellhub.github.neteasemusic.ui.fragments.MusicFragment;
import shellhub.github.neteasemusic.ui.fragments.SingleFragment;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.MainView;

@RuntimePermissions
public class MainActivity extends BaseApp
        implements NavigationView.OnNavigationItemSelectedListener, MainView {
    private final String TAG = TagUtils.getTag(this.getClass());

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

    private Bundle receiveData;

    private MainPresenter mainPresenter;

    @Inject
    public NetEaseMusicService mNetEaseMusicService;

    private MusicFragment musicFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle("");
        LogUtils.d(TagUtils.getTag(MainActivity.class), "loadMusicMenu" + Thread.currentThread().getId());


        setUpNavHeader();
        receiveData = getIntent().getExtras();
        MainActivityPermissionsDispatcher.setUpMVPWithPermissionCheck(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void init() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicMenuEvent(MusicMenuIndexEvent event) {
        mainPresenter.musicMenuNavigate(event);
    }

    ;

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
    public void navigateToLocalView() {
        ActivityUtils.startActivity(LocalActivity.class);
    }

    @Override
    public void navigateToRecentPlayView() {
        ActivityUtils.startActivity(RecentPlayActivity.class);
    }

    @Override
    public void navigateToDownloadsView() {
        ActivityUtils.startActivity(ManageDownloadsActivity.class);
    }

    @Override
    public void navigateToStationsView() {
        ActivityUtils.startActivity(StationsActivity.class);
    }

    @Override
    public void navigateToFavorites() {
        ActivityUtils.startActivity(FavoritesActivity.class);
    }

    @Override
    public void showNetworkError(String errorMsg) {
        ToastUtils.showLong(R.string.network_error);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @Override
    public void setUpMVP() {
        Log.d(TAG, "setUpMVP: ");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new MainFragment())
                .commit();
        mainPresenter = new MainPresenterImpl(mNetEaseMusicService, this);
        mainPresenter.update(receiveData);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void permissionRequest() {
        Log.d(TAG, "setUpMVP: ");
        mainPresenter = new MainPresenterImpl(mNetEaseMusicService, this);
        mainPresenter.update(receiveData);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showRationForStorage(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_storage_ration)
                .setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDeniedForStorage() {
        //TODO
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showNeverAskForStorage() {
        //TODO
        ToastUtils.showLong("You disable this feature");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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


    @Override
    public void updateMusicMenu(List<MusicMenu> musicMenus) {
        LogUtils.d(TAG, musicMenus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                ActivityUtils.startActivity(SearchActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
