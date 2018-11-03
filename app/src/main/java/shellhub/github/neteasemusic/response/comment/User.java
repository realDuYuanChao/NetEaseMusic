package shellhub.github.neteasemusic.response.comment;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User{

	@SerializedName("vipRights")
	private Object vipRights;

	@SerializedName("locationInfo")
	private Object locationInfo;

	@SerializedName("avatarUrl")
	private String avatarUrl;

	@SerializedName("authStatus")
	private int authStatus;

	@SerializedName("nickname")
	private String nickname;

	@SerializedName("vipType")
	private int vipType;

	@SerializedName("expertTags")
	private Object expertTags;

	@SerializedName("remarkName")
	private Object remarkName;

	@SerializedName("userType")
	private int userType;

	@SerializedName("userId")
	private int userId;

	@SerializedName("experts")
	private Object experts;
}