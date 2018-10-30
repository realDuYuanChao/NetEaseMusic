package shellhub.github.neteasemusic.model.impl;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

import shellhub.github.neteasemusic.model.MainModel;
import shellhub.github.neteasemusic.model.entities.NavProfile;
import shellhub.github.neteasemusic.model.entities.User;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.response.login.LoginResponse;
import shellhub.github.neteasemusic.response.login.Profile;
import shellhub.github.neteasemusic.util.AccountUtils;
import shellhub.github.neteasemusic.util.ConstantUtils;

public class MainModelImpl implements MainModel {
    private NetEaseMusicService mNetEaseMusicService;
    private String TAG = MainModelImpl.class.getSimpleName();
    public MainModelImpl(NetEaseMusicService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    @Override
    public void update(Bundle bundle, final MainCallback callback) {
        String uid;
        //login data
        if (bundle != null && bundle.getSerializable(ConstantUtils.LOGIN_RESPONSE_KEY) != null){
            LoginResponse loginResponse = (LoginResponse) bundle.getSerializable(ConstantUtils.LOGIN_RESPONSE_KEY);
            Profile profile = loginResponse.getProfile();
            uid = profile.getUserId() + "";
            callback.updateProfile(new NavProfile(profile.getBackgroundUrl(), profile.getAvatarUrl(), profile.getNickname()));
        }else {
            // no data provide, need call network
            User user = AccountUtils.restore();
            uid = user.getUid();
            mNetEaseMusicService.login(user.getUsername(), user.getPassword(), new NetEaseMusicService.Callback<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse data) {
                    Profile profile = data.getProfile();
                    callback.updateProfile(new NavProfile(profile.getBackgroundUrl(), profile.getAvatarUrl(), profile.getNickname()));
                }

                @Override
                public void onError(Throwable e) {
                    callback.showNetworkError();
                    LogUtils.e(TAG, e.fillInStackTrace());
                }
            });
        }

        //updateMusicMenu detail
        mNetEaseMusicService.detail(uid, new NetEaseMusicService.Callback<DetailResponse>() {
            @Override
            public void onSuccess(DetailResponse detailResponse) {
                callback.updateDetail(detailResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.showNetworkError();
                LogUtils.e(TAG, e.fillInStackTrace());
            }
        });
    }
}
