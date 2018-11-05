package shellhub.github.neteasemusic.ui.activities;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.CommentAdapter;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.presenter.CommentPresenter;
import shellhub.github.neteasemusic.presenter.impl.CommentPresenterImpl;
import shellhub.github.neteasemusic.response.comment.CommentsItem;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.CommentView;

public class CommentActivity extends BaseApp implements CommentView {
    private String TAG = TagUtils.getTag(this.getClass());
    private CommentPresenter mCommentPresenter;

    @BindView(R.id.rv_comment)
    RecyclerView rvComment;

    @Inject
    NetEaseMusicService mNetEaseMusicService;

    private CommentAdapter commentAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);


        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setAdapter(commentAdapter= new CommentAdapter());

        setUpMVP();
    }

    @Override
    public void setUpMVP() {
        LogUtils.d(TAG, mNetEaseMusicService == null);
        mCommentPresenter = new CommentPresenterImpl(mNetEaseMusicService, this);
        mCommentPresenter.loadComment(514055266);
    }

    @Override
    public void onLoaded(List<CommentsItem> commentsItems) {
        LogUtils.d(TAG, commentsItems);
        commentAdapter.setCommentsItems(commentsItems);
        commentAdapter.notifyDataSetChanged();
    }
}
