package shellhub.github.neteasemusic.response.search.song.detail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Al{

	@SerializedName("picUrl")
	private String picUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("tns")
	private List<Object> tns;

	@SerializedName("pic_str")
	private String picStr;

	@SerializedName("id")
	private int id;

	@SerializedName("pic")
	private long pic;
}