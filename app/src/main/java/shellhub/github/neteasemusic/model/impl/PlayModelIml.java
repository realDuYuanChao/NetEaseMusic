package shellhub.github.neteasemusic.model.impl;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.PlayModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.TagUtils;

public class PlayModelIml implements PlayModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private NetEaseMusicService mNetEaseMusicService;

    public PlayModelIml(NetEaseMusicService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    private boolean mPlaying = true;
    @Override
    public void deal(View view, PlayCallback callback) {
        switch (view.getId()) {
            case R.id.iv_play_type:
                int resId = 0;
                SPUtils spUtils = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING);
                switch (spUtils.getInt(ConstantUtils.SP_PLAY_TYPE_KEY, 0)) {
                    case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                        spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE);
                        resId = R.drawable.loop_single_black;
                        break;
                    case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                        spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_SHUFFLE_CODE);
                        resId = R.drawable.shuffle_black;
                        break;
                    case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                        spUtils.put(ConstantUtils.SP_PLAY_TYPE_KEY, ConstantUtils.PLAY_MODE_LOOP_ALL_CODE);
                        resId = R.drawable.ic_loop_all_black;
                        break;
                }
                callback.onPlayType(resId);
                break;
            case R.id.iv_previous:
                callback.onPrevious();
                break;
            case R.id.iv_play_pause:
                //TODO
                if (!mPlaying) {
                    callback.onPlay();
                    LogUtils.d(TAG, "onPlay");
                } else {
                    callback.onPause();
                    LogUtils.d(TAG, "onPause");
                }
                mPlaying = !mPlaying;
                break;
            case R.id.iv_next:
                callback.onNext();
                break;
            case R.id.iv_playlist:
                callback.onPlaylist();
                break;
            case R.id.iv_favorite:
                callback.onFavorite();
                break;
            case R.id.iv_download:
                callback.onDownload();
                break;
            case R.id.iv_comment:
                callback.onComment();
                break;
            case R.id.iv_menu:
                callback.onMenu();
                break;
        }
    }

    @Override
    public void getSongUrl(int id, PlayCallback callback) {
        mNetEaseMusicService.getSongUrl(id, new NetEaseMusicService.Callback<SongResponse>(){

            @Override
            public void onSuccess(SongResponse data) {
                callback.onSongUrl(data.getData().get(0).getUrl());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void getPicUrl(int id, PlayCallback callback) {
        mNetEaseMusicService.getSongDetail(id, new NetEaseMusicService.Callback<SongDetailResponse>(){
            @Override
            public void onSuccess(SongDetailResponse data) {
                callback.onPicUrl(data.getSongs().get(0).getAl().getPicUrl());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
