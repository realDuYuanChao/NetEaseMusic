package shellhub.github.neteasemusic.util;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class ActivityUtils {

    /**
     * make Activity fullscreen
     * @param activity will fullscreen
     * @param showStatusBar show status bar if true
     */
    public static void requestFullScreen(Activity activity, boolean showStatusBar) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (showStatusBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
        } else {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
//
//    public static void checkLogin(Class<? extends Activity> clazz, boolean finish) {
//
//    }
}
