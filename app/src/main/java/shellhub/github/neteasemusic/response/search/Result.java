package shellhub.github.neteasemusic.response.search;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Result{

	@SerializedName("songs")
	private List<SongsItem> songs;

	@SerializedName("songCount")
	private int songCount;
}