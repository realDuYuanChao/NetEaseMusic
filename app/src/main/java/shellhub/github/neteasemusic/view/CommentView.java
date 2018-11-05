package shellhub.github.neteasemusic.view;

import java.util.List;

import shellhub.github.neteasemusic.response.comment.CommentsItem;

public interface CommentView extends BaseView{
    void onLoaded(List<CommentsItem> commentsItems);
}
