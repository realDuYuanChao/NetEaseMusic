package shellhub.github.neteasemusic.response.detail;

import com.google.gson.annotations.SerializedName;

public class UserPoint{

	@SerializedName("balance")
	private int balance;

	@SerializedName("blockBalance")
	private int blockBalance;

	@SerializedName("updateTime")
	private long updateTime;

	@SerializedName("userId")
	private int userId;

	@SerializedName("version")
	private int version;

	@SerializedName("status")
	private int status;
}