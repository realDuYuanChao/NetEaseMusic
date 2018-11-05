package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Data;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.response.comment.CommentsItem;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<CommentsItem> commentsItems = new ArrayList<>();
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        ButterKnife.bind(this, view);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        LogUtils.d(TAG, commentsItems.size());
        LogUtils.d(TAG, commentsItems);
        return commentsItems.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_comment_profile)
        ImageView ivCommentProfile;

        @BindView(R.id.tv_comment_username)
        TextView tvCommentUsername;

        @BindView(R.id.tv_comment_date)
        TextView tvCommentDate;

        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;

        @BindView(R.id.tv_comment_like_counter)
        TextView tvCommentLikeCount;

        @BindView(R.id.iv_comment_like)
        ImageView ivCommentLike;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int index) {
            CommentsItem commentsItem = commentsItems.get(index);
            Glide.with(itemView).load(commentsItem.getUser().getAvatarUrl()).into(ivCommentProfile);
            tvCommentUsername.setText(commentsItem.getUser().getNickname());
            tvCommentDate.setText(new Date(commentsItem.getTime()).toString());
            tvCommentContent.setText(commentsItem.getContent());
            tvCommentLikeCount.setText(commentsItem.getLikedCount() + "");
        }
    }
}
