package shellhub.github.neteasemusic.model;

import android.view.View;

public interface AccountModel {
    void navigate(View view, Callback callback);

    interface Callback {
        void onLogin();

        void onRegister();

        void onEmail();

        void onWechat();

        void onQQ();

        void onWeibo();
    }
}
