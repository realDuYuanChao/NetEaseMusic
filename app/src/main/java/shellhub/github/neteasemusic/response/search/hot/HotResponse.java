package shellhub.github.neteasemusic.response.search.hot;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class HotResponse{

	@SerializedName("result")
	private Result result;

	@SerializedName("code")
	private int code;
}