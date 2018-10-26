package shellhub.github.neteasemusic.response.login;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import shellhub.github.neteasemusic.response.common.BindingsItem;

@Data
public class LoginSuccessResponse implements Serializable {

	@SerializedName("code")
	private int code;

	@SerializedName("loginType")
	private int loginType;

	@SerializedName("profile")
	private Profile profile;

	@SerializedName("bindings")
	private List<BindingsItem> bindings;

	@SerializedName("account")
	private Account account;
}