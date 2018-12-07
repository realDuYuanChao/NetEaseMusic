package shellhub.github.neteasemusic.response.playlist;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Generated("com.robohorse.robopojogenerator")
public class ArItem{

	@SerializedName("name")
	private String name;

	@SerializedName("tns")
	private List<Object> tns;

	@SerializedName("alias")
	private List<Object> alias;

	@SerializedName("id")
	private int id;
}