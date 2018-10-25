package shellhub.github.neteasemusic.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.util.ActivityUtils;

public class AccountActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.requestFullScreen(this, true);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_mobile_login, R.id.btn_register})
    public void loginClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mobile_login:
                com.blankj.utilcode.util.ActivityUtils.startActivity(LoginActivity.class);
                break;
            case R.id.btn_register:
                com.blankj.utilcode.util.ActivityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }
}
