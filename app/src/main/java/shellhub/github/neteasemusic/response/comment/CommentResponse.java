package shellhub.github.neteasemusic.response.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CommentResponse{

	@SerializedName("topComments")
	private List<Object> topComments;

	@SerializedName("total")
	private int total;

	@SerializedName("code")
	private int code;

	@SerializedName("comments")
	private List<Comment> comments;

	@SerializedName("hotComments")
	private List<Comment> hotComments;

	@SerializedName("more")
	private boolean more;

	@SerializedName("userId")
	private int userId;

	@SerializedName("moreHot")
	private boolean moreHot;

	@SerializedName("isMusician")
	private boolean isMusician;
}