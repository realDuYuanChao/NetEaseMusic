package shellhub.github.neteasemusic.response.comment;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class MusicPackage{

	@SerializedName("rights")
	private boolean rights;

	@SerializedName("vipCode")
	private int vipCode;
}