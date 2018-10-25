package shellhub.github.neteasemusic.model.login;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Account{

	@SerializedName("salt")
	private String salt;

	@SerializedName("vipType")
	private int vipType;

	@SerializedName("userName")
	private String userName;

	@SerializedName("type")
	private int type;

	@SerializedName("ban")
	private int ban;

	@SerializedName("anonimousUser")
	private boolean anonimousUser;

	@SerializedName("createTime")
	private long createTime;

	@SerializedName("tokenVersion")
	private int tokenVersion;

	@SerializedName("id")
	private int id;

	@SerializedName("whitelistAuthority")
	private int whitelistAuthority;

	@SerializedName("baoyueVersion")
	private int baoyueVersion;

	@SerializedName("viptypeVersion")
	private int viptypeVersion;

	@SerializedName("donateVersion")
	private int donateVersion;

	@SerializedName("status")
	private int status;

}