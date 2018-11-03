package shellhub.github.neteasemusic.presenter.impl;

import com.blankj.utilcode.util.LogUtils;

import shellhub.github.neteasemusic.model.CommentModel;
import shellhub.github.neteasemusic.model.impl.CommentModelImpl;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.CommentPresenter;
import shellhub.github.neteasemusic.response.comment.CommentResponse;
import shellhub.github.neteasemusic.view.CommentView;

public class CommentPresenterImpl implements CommentPresenter, CommentModel.Callback {
    private CommentView mCommentView;
    private CommentModel mCommentModel;
    private NetEaseMusicService mNetEaseMusicService;

    public CommentPresenterImpl(CommentView commentView) {
        this.mCommentView = commentView;
        this.mCommentModel = new CommentModelImpl(mNetEaseMusicService);
    }

    public CommentPresenterImpl(NetEaseMusicService netEaseMusicService, CommentView commentView) {
        this.mNetEaseMusicService = netEaseMusicService;
        this.mCommentView = commentView;
        this.mCommentModel = new CommentModelImpl(mNetEaseMusicService);
    }

    @Override
    public void loadComment(int id) {
        mCommentModel.loadComment(id, this);
    }

    @Override
    public void onLoaded(CommentResponse commentResponse) {

    }

    @Override
    public void onFailed() {

    }
}
