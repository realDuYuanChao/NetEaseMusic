package shellhub.github.neteasemusic.util;

public class ConstantUtils {
    public final static int PHONE_NUMBER_LENGTH = 11; // China phone number length is 11

    /**
     * NetEase Music API URL
     */
    public final static String BASE_URL = "http://192.168.199.191:3000";
    public final static String CELLPHONE_API = "/login/cellphone";
    public final static String USER_DETAIL_API = "/user/detail";
    public final static String SEARCH_API = "/search";
    public final static String COMMENT_API = "/comment/music";


    /**
     * HTTP STATUS CODE
     */
    public final static int SUCCESS = 200;
    public final static int ACCOUNT_NOT_EXISTS = 501;
    public final static int INCORRECT_PASSWORD = 502;

    /**
     * HTTP Response
     */
    public final static String LOGIN_RESPONSE_KEY = "shellhub.github.neteasemusic_login_response_key";

    /**
     * SharedPreferences
     */
    public final static String SP_LOGIN_USER = "shellhub.github.neteasemusic_login";
    public final static String SP_LOGIN_USER_UID_KEY = "uid";
    public final static String SP_LOGIN_USER_USERNAME_KEY = "username";
    public final static String SP_LOGIN_USER_PASSWORD_KEY = "password";
    public final static String SP_NET_EASE_MUSIC_SETTING = "shellhub.github.neteasemusic_sp_net_ease_music_setting";
    public final static String SP_PLAY_TYPE_KEY = "play_type";

    /**
     * message deliver key
     */
    public final static String MUSIC_URI_KEY = "shellhub.github.neteasemusic_music_uri";

    /**
     * Music Player Action Command
     */
    public static final String ACTION_PLAY = "shellhub.github.neteasemusic.ACTION_PLAY";
    public static final String ACTION_PAUSE = "shellhub.github.neteasemusic.ACTION_PAUSE";
    public static final String ACTION_PREVIOUS = "shellhub.github.neteasemusic.ACTION_PREVIOUS";
    public static final String ACTION_NEXT = "shellhub.github.neteasemusic.ACTION_NEXT";
    public static final String ACTION_STOP = "shellhub.github.neteasemusic.ACTION_STOP";
    public static final String ACTION_DOWNLOAD = "shellhub.github.neteasemusic.ACTION_DOWNLOAD";

    /**
     * Music Player Play Type
     */
    public static final int PLAY_MODE_LOOP_ALL_CODE = 0;
    public static final int PLAY_MODE_LOOP_SINGLE_CODE = 1;
    public static final int PLAY_MODE_SHUFFLE_CODE = 2;


    /**
     * Duration Constant
     */
    public static final int ONE_HOUR = 60 * 60 * 1000;
    public static final int ONE_MINUTE = 60 * 1000;
    public static final int ONE_SECOND = 1000;

}
