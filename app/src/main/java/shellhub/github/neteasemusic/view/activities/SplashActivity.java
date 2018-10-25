package shellhub.github.neteasemusic.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presentation.SplashPresenter;
import shellhub.github.neteasemusic.presentation.impl.SplashPresenterImpl;
import shellhub.github.neteasemusic.util.ActivityUtils;
import shellhub.github.neteasemusic.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {

    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.requestFullScreen(this, false);
        overridePendingTransition(R.anim.slide_out_up, R.anim.slide_in_up);
        setContentView(R.layout.activity_splash);
        setUpMVP();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashPresenter.checkLogin();
            }
        }, 1000);
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void setUpMVP() {
        mSplashPresenter = new SplashPresenterImpl(this);
    }
}
