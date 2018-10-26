package shellhub.github.neteasemusic.response.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import shellhub.github.neteasemusic.response.common.Experts;

@Data
public class Profile{

	@SerializedName("backgroundUrl")
	private String backgroundUrl;

	@SerializedName("birthday")
	private long birthday;

	@SerializedName("detailDescription")
	private String detailDescription;

	@SerializedName("gender")
	private int gender;

	@SerializedName("city")
	private int city;

	@SerializedName("signature")
	private String signature;

	@SerializedName("followeds")
	private int followeds;

	@SerializedName("description")
	private String description;

	@SerializedName("remarkName")
	private Object remarkName;

	@SerializedName("eventCount")
	private int eventCount;

	@SerializedName("playlistBeSubscribedCount")
	private int playlistBeSubscribedCount;

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

	@SerializedName("artistIdentity")
	private List<Object> artistIdentity;

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

	@SerializedName("follows")
	private int follows;

	@SerializedName("vipType")
	private int vipType;

	@SerializedName("blacklist")
	private boolean blacklist;

	@SerializedName("userId")
	private int userId;

	@SerializedName("followed")
	private boolean followed;

	@SerializedName("mutual")
	private boolean mutual;

	@SerializedName("authority")
	private int authority;

	@SerializedName("artistName")
	private Object artistName;

	@SerializedName("backgroundImgId")
	private long backgroundImgId;

	@SerializedName("userType")
	private int userType;

	@SerializedName("experts")
	private Experts experts;

	@SerializedName("playlistCount")
	private int playlistCount;
}