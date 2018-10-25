package shellhub.github.neteasemusic.presentation.impl;

import android.text.TextUtils;

import com.blankj.utilcode.util.ActivityUtils;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.User;
import shellhub.github.neteasemusic.presentation.SplashPresenter;
import shellhub.github.neteasemusic.util.AccountUtils;
import shellhub.github.neteasemusic.view.SplashView;
import shellhub.github.neteasemusic.view.activities.LoginActivity;
import shellhub.github.neteasemusic.view.activities.MainActivity;

public class SplashPresenterImpl implements SplashPresenter {
    private SplashView mSplashView;

    public SplashPresenterImpl(SplashView mSplashView) {
        this.mSplashView = mSplashView;
    }

    @Override
    public void checkLogin() {
        User user = AccountUtils.restore();
        if (TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
//            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
//            ActivityUtils.startActivity(LoginActivity.class, R.anim.zoom_in, R.anim.zoom_out);
            ActivityUtils.startActivity(LoginActivity.class, R.anim.slide_in_up, R.anim.slide_out_up);

        }else{
//            ActivityUtils.startActivity(MainActivity.class, R.anim.zoom_in, R.anim.zoom_out);
            ActivityUtils.startActivity(MainActivity.class, R.anim.slide_in_up, R.anim.slide_out_up);
        }
    }
}
