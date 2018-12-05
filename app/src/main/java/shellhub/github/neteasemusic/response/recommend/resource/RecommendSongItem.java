package shellhub.github.neteasemusic.response.recommend.resource;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class RecommendSongItem {

	@SerializedName("picUrl")
	private String picUrl;

	@SerializedName("creator")
	private Creator creator;

	@SerializedName("trackCount")
	private int trackCount;

	@SerializedName("createTime")
	private long createTime;

	@SerializedName("playcount")
	private int playcount;

	@SerializedName("name")
	private String name;

	@SerializedName("copywriter")
	private String copywriter;

	@SerializedName("id")
	private long id;

	@SerializedName("type")
	private int type;

	@SerializedName("userId")
	private int userId;

	@SerializedName("alg")
	private String alg;
}