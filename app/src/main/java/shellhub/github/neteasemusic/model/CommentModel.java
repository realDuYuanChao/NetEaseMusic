package shellhub.github.neteasemusic.model;

import shellhub.github.neteasemusic.response.comment.CommentResponse;

public interface CommentModel {
    void loadComment(int id, Callback callback);

    interface Callback{
        void onLoaded(CommentResponse commentResponse);

        void onFailed();
    }
}
