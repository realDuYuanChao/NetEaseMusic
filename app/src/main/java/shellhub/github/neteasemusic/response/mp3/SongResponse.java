package shellhub.github.neteasemusic.response.mp3;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SongResponse implements Serializable {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItem> data;
}