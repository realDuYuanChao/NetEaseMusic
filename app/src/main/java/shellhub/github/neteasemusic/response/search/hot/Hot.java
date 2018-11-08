package shellhub.github.neteasemusic.response.search.hot;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Hot {

	@SerializedName("third")
	private Object third;

	@SerializedName("iconType")
	private int iconType;

	@SerializedName("first")
	private String first;

	@SerializedName("second")
	private int second;
}