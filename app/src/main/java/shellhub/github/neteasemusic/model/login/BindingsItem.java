package shellhub.github.neteasemusic.model.login;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BindingsItem{

	@SerializedName("expiresIn")
	private int expiresIn;

	@SerializedName("expired")
	private boolean expired;

	@SerializedName("tokenJsonStr")
	private String tokenJsonStr;

	@SerializedName("refreshTime")
	private int refreshTime;

	@SerializedName("id")
	private long id;

	@SerializedName("type")
	private int type;

	@SerializedName("userId")
	private int userId;

	@SerializedName("url")
	private String url;
}