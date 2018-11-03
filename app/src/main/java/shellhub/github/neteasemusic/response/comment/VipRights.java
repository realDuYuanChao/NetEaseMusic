package shellhub.github.neteasemusic.response.comment;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class VipRights{

	@SerializedName("associator")
	private Associator associator;

	@SerializedName("musicPackage")
	private MusicPackage musicPackage;
}