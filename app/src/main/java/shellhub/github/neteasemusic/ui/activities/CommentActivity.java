package shellhub.github.neteasemusic.ui.activities;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.CommentPresenter;
import shellhub.github.neteasemusic.presenter.impl.CommentPresenterImpl;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.CommentView;

public class CommentActivity extends BaseApp implements CommentView {
    private String TAG = TagUtils.getTag(this.getClass());
    private CommentPresenter mCommentPresenter;

    @Inject
    NetEaseMusicService mNetEaseMusicService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setUpMVP();
    }

    @Override
    public void setUpMVP() {
        LogUtils.d(TAG, mNetEaseMusicService == null);
        mCommentPresenter = new CommentPresenterImpl(mNetEaseMusicService, this);
        mCommentPresenter.loadComment(387717);
    }
}
