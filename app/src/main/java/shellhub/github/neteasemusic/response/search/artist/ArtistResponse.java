package shellhub.github.neteasemusic.response.search.artist;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ArtistResponse{

	@SerializedName("result")
	private Result result;

	@SerializedName("code")
	private int code;
}