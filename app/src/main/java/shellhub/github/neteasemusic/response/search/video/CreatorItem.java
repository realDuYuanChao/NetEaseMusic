package shellhub.github.neteasemusic.response.search.video;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class CreatorItem{

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private int userId;
}