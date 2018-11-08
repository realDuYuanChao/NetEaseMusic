package shellhub.github.neteasemusic.response.search.hot;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Result{

	@SerializedName("hots")
	private List<Hot> hots;
}