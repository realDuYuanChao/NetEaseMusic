package shellhub.github.neteasemusic.response.mp3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataItem implements Serializable {

	@SerializedName("code")
	private int code;

	@SerializedName("expi")
	private int expi;

	@SerializedName("flag")
	private int flag;

	@SerializedName("fee")
	private int fee;

	@SerializedName("type")
	private String type;

	@SerializedName("canExtend")
	private boolean canExtend;

	@SerializedName("url")
	private String url;

	@SerializedName("gain")
	private double gain;

	@SerializedName("br")
	private int br;

	@SerializedName("uf")
	private Object uf;

	@SerializedName("size")
	private int size;

	@SerializedName("id")
	private int id;

	@SerializedName("md5")
	private String md5;

	@SerializedName("payed")
	private int payed;

	@SerializedName("freeTrialInfo")
	private Object freeTrialInfo;
}