package shellhub.github.neteasemusic.util;

public class ConstantUtils {
    public final static int PHONE_NUMBER_LENGTH = 11; // China phone number length is 11

    /**
     * NetEase Music API URL
     */
    public final static String BASE_URL = "http://192.168.199.191:3000";
    public final static String CELLPHONE_API = "/login/cellphone";

    /**
     * HTTP STATUS CODE
     */
    public final static int SUCCESS = 200;
    public final static int ACCOUNT_NOT_EXISTS = 501;
    public final static int INCORRECT_PASSWORD = 502;

    /**
     * SharedPreferences
     */
    public final static String SP_LOGIN_USER = "shellhub.github.neteasemusic_login";
    public final static String SP_LOGIN_USER_USERNAME_KEY = "username";
    public final static String SP_LOGIN_USER_PASSWORD_KEY = "password";

}
