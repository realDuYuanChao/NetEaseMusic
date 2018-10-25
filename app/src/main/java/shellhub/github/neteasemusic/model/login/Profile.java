package shellhub.github.neteasemusic.model.login;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Profile{

	@SerializedName("detailDescription")
	private String detailDescription;

	@SerializedName("birthday")
	private long birthday;

	@SerializedName("backgroundUrl")
	private String backgroundUrl;

	@SerializedName("gender")
	private int gender;

	@SerializedName("city")
	private int city;

	@SerializedName("signature")
	private String signature;

	@SerializedName("description")
	private String description;

	@SerializedName("remarkName")
	private Object remarkName;

	@SerializedName("accountStatus")
	private int accountStatus;

	@SerializedName("avatarImgId")
	private long avatarImgId;

	@SerializedName("defaultAvatar")
	private boolean defaultAvatar;

	@SerializedName("avatarImgIdStr")
	private String avatarImgIdStr;

	@SerializedName("backgroundImgIdStr")
	private String backgroundImgIdStr;

	@SerializedName("province")
	private int province;

	@SerializedName("nickname")
	private String nickname;

	@SerializedName("expertTags")
	private Object expertTags;

	@SerializedName("djStatus")
	private int djStatus;

	@SerializedName("avatarUrl")
	private String avatarUrl;

	@SerializedName("authStatus")
	private int authStatus;

	@SerializedName("vipType")
	private int vipType;

	@SerializedName("followed")
	private boolean followed;

	@SerializedName("userId")
	private int userId;

	@SerializedName("mutual")
	private boolean mutual;

	@SerializedName("authority")
	private int authority;

	@SerializedName("backgroundImgId")
	private long backgroundImgId;

	@SerializedName("userType")
	private int userType;

	@SerializedName("experts")
	private Experts experts;

}