package shellhub.github.neteasemusic.response.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import shellhub.github.neteasemusic.response.common.BindingsItem;

@Data
public class DetailResponse{

	@SerializedName("userPoint")
	private UserPoint userPoint;

	@SerializedName("adValid")
	private boolean adValid;

	@SerializedName("code")
	private int code;

	@SerializedName("level")
	private int level;

	@SerializedName("createTime")
	private long createTime;

	@SerializedName("listenSongs")
	private int listenSongs;

	@SerializedName("createDays")
	private int createDays;

	@SerializedName("profile")
	private Profile profile;

	@SerializedName("bindings")
	private List<BindingsItem> bindings;

	@SerializedName("pcSign")
	private boolean pcSign;

	@SerializedName("mobileSign")
	private boolean mobileSign;

	@SerializedName("peopleCanSeeMyPlayRecord")
	private boolean peopleCanSeeMyPlayRecord;
}