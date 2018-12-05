package shellhub.github.neteasemusic.response.recommend.resource;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class RecommendSongListResponse{

	@SerializedName("haveRcmdSongs")
	private boolean haveRcmdSongs;

	@SerializedName("code")
	private int code;

	@SerializedName("featureFirst")
	private boolean featureFirst;

	@SerializedName("recommend")
	private List<RecommendSongItem> recommend;
}