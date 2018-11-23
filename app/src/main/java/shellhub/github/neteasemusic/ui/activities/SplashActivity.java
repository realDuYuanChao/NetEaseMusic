package shellhub.github.neteasemusic.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.SplashPresenter;
import shellhub.github.neteasemusic.presenter.impl.SplashPresenterImpl;
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

        new Handler().postDelayed(()->{
            mSplashPresenter.navigate();
        } , 0);
    }

    @Override
    public void setUpMVP() {
        mSplashPresenter = new SplashPresenterImpl(this);
    }

    @Override
    public void navigateMain() {
        com.blankj.utilcode.util.ActivityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void navigateLogin() {
        com.blankj.utilcode.util.ActivityUtils.startActivity(LoginActivity.class);
    }
}
