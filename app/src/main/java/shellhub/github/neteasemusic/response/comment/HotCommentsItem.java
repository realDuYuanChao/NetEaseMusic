package shellhub.github.neteasemusic.response.comment;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class HotCommentsItem{

	@SerializedName("beReplied")
	private List<Object> beReplied;

	@SerializedName("commentId")
	private int commentId;

	@SerializedName("likedCount")
	private int likedCount;

	@SerializedName("time")
	private long time;

	@SerializedName("user")
	private User user;

	@SerializedName("liked")
	private boolean liked;

	@SerializedName("expressionUrl")
	private Object expressionUrl;

	@SerializedName("content")
	private String content;

	@SerializedName("pendantData")
	private Object pendantData;
}