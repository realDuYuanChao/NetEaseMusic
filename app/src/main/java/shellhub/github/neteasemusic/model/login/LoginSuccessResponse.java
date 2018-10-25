package shellhub.github.neteasemusic.model.login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class LoginSuccessResponse{

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