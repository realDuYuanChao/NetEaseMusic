package shellhub.github.neteasemusic.ui.activities;

import android.content.Intent;
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
        setContentView(R.layout.activity_splash);
        setUpMVP();

        new Handler().postDelayed(()->{
            mSplashPresenter.navigate();
        } , 500);
    }

    @Override
    public void setUpMVP() {
        mSplashPresenter = new SplashPresenterImpl(this);
    }

    @Override
    public void navigateMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateLogin() {
        //todo
    }

    @Override
    public void navigateAccount() {
        startActivity(new Intent(this, AccountActivity.class));
        finish();
    }
}
