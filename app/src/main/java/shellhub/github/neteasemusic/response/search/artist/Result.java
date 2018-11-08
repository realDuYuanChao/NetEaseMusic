package shellhub.github.neteasemusic.response.search.artist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Result{

	@SerializedName("artistCount")
	private int artistCount;

	@SerializedName("artists")
	private List<ArtistsItem> artists;
}