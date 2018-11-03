package shellhub.github.neteasemusic.response.comment;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BeRepliedItem{

	@SerializedName("user")
	private User user;

	@SerializedName("content")
	private String content;

	@SerializedName("expressionUrl")
	private Object expressionUrl;

	@SerializedName("status")
	private int status;
}