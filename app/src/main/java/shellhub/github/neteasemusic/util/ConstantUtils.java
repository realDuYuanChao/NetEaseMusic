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
    public final static String SEARCH_HOT_API = "/search/hot";
    public final static String SONG_URL_API = "/song/url";
    public final static String SONG_DETAIL_API = "/song/detail";


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
    public final static String SP_NET_EASE_MUSIC_STATUS = "shellhub.github.neteasemusic_sp_net_ease_music_status";
    public final static String SP_PLAY_TYPE_KEY = "play_type";
    public final static String SP_CURRENT_SONG_ID_KEY = "song_id";
    public final static String SP_CURRENT_SONG_URL_KEY = "song_url";
    public final static String SP_CURRENT_SONG_NAME_KEY = "song_name";
    public final static String SP_CURRENT_SONG_ARTIST_AND_ALBUM_KEY = "song_artist_and_album";
    public final static String SP_CURRENT_SONG_ALBUM_URL_KEY = "song_album";
    public final static String SP_CURRENT_IS_PLAYING_STATUS_KEY = "song_play_status";
    public final static String SP_CURRENT_PLAYLIST_INDEX_KEY = "play_list_index";

    /**
     * message deliver key
     */
    public final static String MUSIC_URI_KEY = "shellhub.github.neteasemusic_music_uri";
    public final static String MUSIC_ID_KEY = "shellhub.github.neteasemusic_music_id_key";

    /**
     * Music Player Action Command
     */
    public static final String ACTION_PLAY = "shellhub.github.neteasemusic.ACTION_PLAY";
    public static final String ACTION_PAUSE = "shellhub.github.neteasemusic.ACTION_PAUSE";
    public static final String ACTION_STATUS = "shellhub.github.neteasemusic.ACTION_STATUS"; //is playing or not
    public static final String ACTION_PREVIOUS = "shellhub.github.neteasemusic.ACTION_PREVIOUS";
    public static final String ACTION_NEXT = "shellhub.github.neteasemusic.ACTION_NEXT";
    public static final String ACTION_STOP = "shellhub.github.neteasemusic.ACTION_STOP";
    public static final String ACTION_DOWNLOAD = "shellhub.github.neteasemusic.ACTION_DOWNLOAD";
    public static final String ACTION_UPDATE_NOTIFICATION = "shellhub.github.neteasemusic.ACTION_UPDATE_NOTIFICATION";

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
