package shellhub.github.neteasemusic.response.search.video;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Result{

	@SerializedName("videoCount")
	private int videoCount;

	@SerializedName("videos")
	private List<VideosItem> videos;
}