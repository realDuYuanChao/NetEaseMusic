package shellhub.github.neteasemusic.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //TODO
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void setUpMVP() {

    }
}
