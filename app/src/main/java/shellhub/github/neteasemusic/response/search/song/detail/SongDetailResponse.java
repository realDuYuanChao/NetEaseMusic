package shellhub.github.neteasemusic.response.search.song.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class SongDetailResponse {

	@SerializedName("privileges")
	private List<PrivilegesItem> privileges;

	@SerializedName("code")
	private int code;

	@SerializedName("songs")
	private List<SongsItem> songs;
}