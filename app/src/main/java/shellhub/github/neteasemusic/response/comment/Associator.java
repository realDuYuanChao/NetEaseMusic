package shellhub.github.neteasemusic.response.comment;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Associator{

	@SerializedName("rights")
	private boolean rights;

	@SerializedName("vipCode")
	private int vipCode;
}