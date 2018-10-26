package shellhub.github.neteasemusic.presentation.impl;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

import shellhub.github.neteasemusic.model.NavProfile;
import shellhub.github.neteasemusic.model.User;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.response.login.LoginSuccessResponse;
import shellhub.github.neteasemusic.response.login.Profile;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presentation.MainPresenter;
import shellhub.github.neteasemusic.util.AccountUtils;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.view.MainView;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private NetEaseMusicService mNetEaseMusicService;

    private String TAG = MainPresenter.class.getSimpleName();

    public MainPresenterImpl(NetEaseMusicService netEaseMusicService, MainView mainView) {
        this.mNetEaseMusicService = netEaseMusicService;
        this.mainView = mainView;
    }

    @Override
    public void update(Bundle bundle) {
        String uid;
        //login data
        if (bundle != null && bundle.getSerializable(ConstantUtils.LOGIN_RESPONSE_KEY) != null){
            LoginSuccessResponse loginSuccessResponse = (LoginSuccessResponse) bundle.getSerializable(ConstantUtils.LOGIN_RESPONSE_KEY);
            Profile profile = loginSuccessResponse.getProfile();
            uid = profile.getUserId() + "";
            mainView.updateNavProfile(new NavProfile(profile.getBackgroundUrl(), profile.getAvatarUrl(), profile.getNickname()));
        }else {
            // no data provide, need call network
            User user = AccountUtils.restore();
            uid = user.getUid();
            mNetEaseMusicService.login(user.getUsername(), user.getPassword(), new NetEaseMusicService.Callback<LoginSuccessResponse>() {
                @Override
                public void onSuccess(LoginSuccessResponse data) {
                    Profile profile = data.getProfile();
                    mainView.updateNavProfile(new NavProfile(profile.getBackgroundUrl(), profile.getAvatarUrl(), profile.getNickname()));
                }

                @Override
                public void onError(Throwable e) {
                }
            });
        }

        //update detail
        mNetEaseMusicService.detail(uid, new NetEaseMusicService.Callback<DetailResponse>() {
            @Override
            public void onSuccess(DetailResponse detailResponse) {
                LogUtils.d(TAG, detailResponse);
                mainView.updateDetail(detailResponse);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
