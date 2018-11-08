package shellhub.github.neteasemusic.response.search.artist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ArtistsItem{

	@SerializedName("picUrl")
	private String picUrl;

	@SerializedName("img1v1Url")
	private String img1v1Url;

	@SerializedName("img1v1")
	private long img1v1;

	@SerializedName("name")
	private String name;

	@SerializedName("alias")
	private List<String> alias;

	@SerializedName("id")
	private int id;

	@SerializedName("mvSize")
	private int mvSize;

	@SerializedName("followed")
	private boolean followed;

	@SerializedName("alg")
	private String alg;

	@SerializedName("albumSize")
	private int albumSize;

	@SerializedName("picId")
	private long picId;

	@SerializedName("trans")
	private Object trans;

	@SerializedName("alia")
	private List<String> alia;

	@SerializedName("accountId")
	private int accountId;
}