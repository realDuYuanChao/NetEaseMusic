package shellhub.github.neteasemusic.response.banner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class BannerResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("banners")
	private List<BannersItem> banners;
}