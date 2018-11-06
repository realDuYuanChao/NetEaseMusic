package shellhub.github.neteasemusic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
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
import shellhub.github.neteasemusic.response.comment.Comment;
import shellhub.github.neteasemusic.util.TagUtils;

@Data
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Comment> comments = new ArrayList<>();
    private List<Comment> hotComments = new ArrayList<>();
    private final int TYPE_TOP_COMMENT_HEADER = 0;
    private final int TYPE_TOP_COMMENT = 1;
    private final int TYPE_LATEST_COMMENT_HEADER = 2;
    private final int TYPE_LATEST_COMMENT = 3
            ;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_TOP_COMMENT_HEADER:
                view = inflater.inflate(R.layout.comment_header, parent, false);
                ButterKnife.bind(this, view);
                return new TopCommentHeaderViewHolder(view);
            case TYPE_TOP_COMMENT:
                view = inflater.inflate(R.layout.comment_item, parent, false);
                ButterKnife.bind(this, view);
                return new TopCommentViewHolder(view);
            case TYPE_LATEST_COMMENT_HEADER:
                view = inflater.inflate(R.layout.comment_header, parent, false);
                ButterKnife.bind(this, view);
                return new LatestCommentHeaderViewHolder(view);
            case TYPE_LATEST_COMMENT:
                view = inflater.inflate(R.layout.comment_item, parent, false);
                ButterKnife.bind(this, view);
                return new LatestCommentViewHolder(view);
            default:
                return null; //THIS WON'T EXECUTE
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopCommentHeaderViewHolder) {
            ((TopCommentHeaderViewHolder) holder).tvHeaderName.setText(R.string.top_comments);
        } else if (holder instanceof TopCommentViewHolder) {
            ((TopCommentViewHolder) holder).bind(position - 1);
        } else if ((holder instanceof LatestCommentHeaderViewHolder)) {
            String headerContent = Utils.getApp().getResources().getString(R.string.latest_comments) + "(" + comments.size() + ")";
            ((LatestCommentHeaderViewHolder)holder).tvHeaderName.setText(headerContent);
        } else if (holder instanceof LatestCommentViewHolder) {
            if (position - hotComments.size() - 1 >= 0) {
                ((LatestCommentViewHolder) holder).bind(position - hotComments.size() - 2);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_TOP_COMMENT_HEADER) {
            return TYPE_TOP_COMMENT_HEADER;
        }
        if (position > 0 && position <= hotComments.size()) {
            return TYPE_TOP_COMMENT;
        }
        if (position == hotComments.size() + 1) {
            return TYPE_LATEST_COMMENT_HEADER;
        }
        return TYPE_LATEST_COMMENT;
    }

    @Override
    public int getItemCount() {
        return comments.size() + hotComments.size() + 2;
    }

    public class LatestCommentViewHolder extends RecyclerView.ViewHolder{
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

        public LatestCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int index) {
            Comment comment = comments.get(index);
            Glide.with(itemView).load(comment.getUser().getAvatarUrl()).into(ivCommentProfile);
            tvCommentUsername.setText(comment.getUser().getNickname());
            tvCommentDate.setText(new Date(comment.getTime()).toString());
            tvCommentContent.setText(comment.getContent());
            tvCommentLikeCount.setText(comment.getLikedCount() + "");
        }
    }

    public class TopCommentHeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_comment_header)
        public TextView tvHeaderName;

        public TopCommentHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LatestCommentHeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_comment_header)
        public TextView tvHeaderName;

        public LatestCommentHeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TopCommentViewHolder extends RecyclerView.ViewHolder{
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

        public TopCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int index) {
            Comment comment = hotComments.get(index);
            Glide.with(itemView).load(comment.getUser().getAvatarUrl()).into(ivCommentProfile);
            tvCommentUsername.setText(comment.getUser().getNickname());
            tvCommentDate.setText(new Date(comment.getTime()).toString());
            tvCommentContent.setText(comment.getContent());
            tvCommentLikeCount.setText(comment.getLikedCount() + "");
        }
    }
}
