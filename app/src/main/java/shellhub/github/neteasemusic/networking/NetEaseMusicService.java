package shellhub.github.neteasemusic.networking;


import android.accounts.NetworkErrorException;

import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import shellhub.github.neteasemusic.model.login.LoginSuccessResponse;

public class NetEaseMusicService {
    private NetEaseMusicAPI netEaseMusicAPI;

    public NetEaseMusicService(NetEaseMusicAPI netEaseMusicAPI) {
        this.netEaseMusicAPI = netEaseMusicAPI;
    }

    public void login(String phone, String password, final LoginCallback loginCallback) {
        netEaseMusicAPI.loginByPhone(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginSuccessResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginSuccessResponse loginSuccessResponse) {
                        loginCallback.onSuccess(loginSuccessResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface LoginCallback {
        void onSuccess(LoginSuccessResponse loginSuccessResponse);

        void onError(Throwable e);
    }

}
