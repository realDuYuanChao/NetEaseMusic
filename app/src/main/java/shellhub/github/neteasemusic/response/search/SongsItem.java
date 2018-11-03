package shellhub.github.neteasemusic.response.search;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class SongsItem{

	@SerializedName("album")
	private Album album;

	@SerializedName("fee")
	private int fee;

	@SerializedName("rUrl")
	private Object rUrl;

	@SerializedName("duration")
	private int duration;

	@SerializedName("rtype")
	private int rtype;

	@SerializedName("ftype")
	private int ftype;

	@SerializedName("artists")
	private List<ArtistsItem> artists;

	@SerializedName("copyrightId")
	private int copyrightId;

	@SerializedName("mvid")
	private int mvid;

	@SerializedName("name")
	private String name;

	@SerializedName("alias")
	private List<Object> alias;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private int status;
}