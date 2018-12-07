package shellhub.github.neteasemusic.response.playlist;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class PlaylistDetailResponse{

	@SerializedName("privileges")
	private List<PrivilegesItem> privileges;

	@SerializedName("code")
	private int code;

	@SerializedName("playlist")
	private Playlist playlist;
}