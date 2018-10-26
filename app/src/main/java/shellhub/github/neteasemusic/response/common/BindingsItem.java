package shellhub.github.neteasemusic.response.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class BindingsItem implements Serializable {

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