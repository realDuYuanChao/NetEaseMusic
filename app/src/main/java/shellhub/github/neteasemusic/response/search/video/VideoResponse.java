package shellhub.github.neteasemusic.response.search.video;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class VideoResponse{

	@SerializedName("result")
	private Result result;

	@SerializedName("code")
	private int code;
}