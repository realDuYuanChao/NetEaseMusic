package shellhub.github.neteasemusic.response.playlist;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class Al{

	@SerializedName("picUrl")
	private String picUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("tns")
	private List<Object> tns;

	@SerializedName("id")
	private int id;

	@SerializedName("pic")
	private long pic;

	@SerializedName("pic_str")
	private String picStr;
}