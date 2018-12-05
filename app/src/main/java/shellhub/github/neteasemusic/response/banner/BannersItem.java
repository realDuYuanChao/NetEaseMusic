package shellhub.github.neteasemusic.response.banner;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BannersItem{

	@SerializedName("song")
	private Object song;

	@SerializedName("adLocation")
	private Object adLocation;

	@SerializedName("targetId")
	private int targetId;

	@SerializedName("monitorImpress")
	private Object monitorImpress;

	@SerializedName("extMonitor")
	private Object extMonitor;

	@SerializedName("adSource")
	private Object adSource;

	@SerializedName("targetType")
	private int targetType;

	@SerializedName("typeTitle")
	private String typeTitle;

	@SerializedName("program")
	private Object program;

	@SerializedName("video")
	private Object video;

	@SerializedName("url")
	private Object url;

	@SerializedName("encodeId")
	private String encodeId;

	@SerializedName("monitorType")
	private Object monitorType;

	@SerializedName("extMonitorInfo")
	private Object extMonitorInfo;

	@SerializedName("adid")
	private Object adid;

	@SerializedName("titleColor")
	private String titleColor;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("exclusive")
	private boolean exclusive;

	@SerializedName("monitorClick")
	private Object monitorClick;

	@SerializedName("monitorImpressList")
	private Object monitorImpressList;

	@SerializedName("event")
	private Object event;

	@SerializedName("monitorBlackList")
	private Object monitorBlackList;

	@SerializedName("monitorClickList")
	private Object monitorClickList;
}