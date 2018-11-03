package shellhub.github.neteasemusic.model.impl;

import android.view.View;

import com.blankj.utilcode.util.LogUtils;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.PlayModel;
import shellhub.github.neteasemusic.util.TagUtils;

public class PlayModelIml implements PlayModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private boolean mPlaying = true;
    @Override
    public void deal(View view, PlayCallback callback) {
        switch (view.getId()) {
            case R.id.iv_play_type:
                callback.onPlayType();
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
}
