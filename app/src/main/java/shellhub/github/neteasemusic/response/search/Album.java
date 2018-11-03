package shellhub.github.neteasemusic.response.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Album{

	@SerializedName("publishTime")
	private long publishTime;

	@SerializedName("size")
	private int size;

	@SerializedName("artist")
	private Artist artist;

	@SerializedName("copyrightId")
	private int copyrightId;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("picId")
	private long picId;

	@SerializedName("status")
	private int status;

	@SerializedName("alia")
	private List<String> alia;

}