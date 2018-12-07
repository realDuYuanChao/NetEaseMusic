package shellhub.github.neteasemusic.response.playlist;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class Playlist{

	@SerializedName("privacy")
	private int privacy;

	@SerializedName("description")
	private String description;

	@SerializedName("trackNumberUpdateTime")
	private long trackNumberUpdateTime;

	@SerializedName("subscribed")
	private boolean subscribed;

	@SerializedName("shareCount")
	private int shareCount;

	@SerializedName("adType")
	private int adType;

	@SerializedName("trackCount")
	private int trackCount;

	@SerializedName("coverImgId_str")
	private String coverImgIdStr;

	@SerializedName("specialType")
	private int specialType;

	@SerializedName("trackIds")
	private List<TrackIdsItem> trackIds;

	@SerializedName("id")
	private int id;

	@SerializedName("ordered")
	private boolean ordered;

	@SerializedName("creator")
	private Creator creator;

	@SerializedName("subscribers")
	private List<SubscribersItem> subscribers;

	@SerializedName("highQuality")
	private boolean highQuality;

	@SerializedName("commentThreadId")
	private String commentThreadId;

	@SerializedName("updateTime")
	private long updateTime;

	@SerializedName("trackUpdateTime")
	private long trackUpdateTime;

	@SerializedName("userId")
	private int userId;

	@SerializedName("tracks")
	private List<TracksItem> tracks;

	@SerializedName("tags")
	private List<String> tags;

	@SerializedName("commentCount")
	private int commentCount;

	@SerializedName("coverImgUrl")
	private String coverImgUrl;

	@SerializedName("cloudTrackCount")
	private int cloudTrackCount;

	@SerializedName("playCount")
	private int playCount;

	@SerializedName("coverImgId")
	private long coverImgId;

	@SerializedName("createTime")
	private long createTime;

	@SerializedName("name")
	private String name;

	@SerializedName("subscribedCount")
	private int subscribedCount;

	@SerializedName("newImported")
	private boolean newImported;

	@SerializedName("status")
	private int status;
}