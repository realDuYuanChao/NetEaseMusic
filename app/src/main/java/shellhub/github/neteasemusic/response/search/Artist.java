package shellhub.github.neteasemusic.response.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Artist{

	@SerializedName("picUrl")
	private Object picUrl;

	@SerializedName("img1v1Url")
	private String img1v1Url;

	@SerializedName("img1v1")
	private int img1v1;

	@SerializedName("name")
	private String name;

	@SerializedName("alias")
	private List<Object> alias;

	@SerializedName("id")
	private int id;

	@SerializedName("albumSize")
	private int albumSize;

	@SerializedName("picId")
	private int picId;

	@SerializedName("trans")
	private Object trans;

}