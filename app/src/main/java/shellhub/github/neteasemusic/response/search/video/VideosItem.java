package shellhub.github.neteasemusic.response.search.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class VideosItem{

	@SerializedName("coverUrl")
	private String coverUrl;

	@SerializedName("transName")
	private Object transName;

	@SerializedName("vid")
	private String vid;

	@SerializedName("creator")
	private List<CreatorItem> creator;

	@SerializedName("aliaName")
	private Object aliaName;

	@SerializedName("markTypes")
	private List<Object> markTypes;

	@SerializedName("playTime")
	private int playTime;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private int type;

	@SerializedName("durationms")
	private int durationms;

	@SerializedName("alg")
	private String alg;
}