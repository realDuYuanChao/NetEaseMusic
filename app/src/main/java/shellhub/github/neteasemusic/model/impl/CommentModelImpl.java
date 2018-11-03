package shellhub.github.neteasemusic.model.impl;

import com.blankj.utilcode.util.LogUtils;

import java.awt.font.TextAttribute;

import shellhub.github.neteasemusic.model.CommentModel;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.comment.CommentResponse;
import shellhub.github.neteasemusic.util.TagUtils;

public class CommentModelImpl implements CommentModel {
    private String TAG = TagUtils.getTag(this.getClass());
    private NetEaseMusicService mNetEaseMusicService;

    public CommentModelImpl(NetEaseMusicService mNetEaseMusicService) {
        this.mNetEaseMusicService = mNetEaseMusicService;
    }

    @Override
    public void loadComment(int id, Callback callback) {
        LogUtils.d(TAG, id);
        mNetEaseMusicService.comment(id, new NetEaseMusicService.Callback<CommentResponse>() {
            @Override
            public void onSuccess(CommentResponse data) {
                LogUtils.d(TAG, data);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(TAG, e.fillInStackTrace());
                callback.onFailed();
            }
        });
    }

}
