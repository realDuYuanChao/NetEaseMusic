package shellhub.github.neteasemusic.util;

public class TagUtils {
    public static String getTag(Class clazz) {
        return clazz.getSimpleName();
    }

    public static String getTag(Class clazz, boolean withPackage) {
        if (!withPackage) {
            return getTag(clazz);
        } else {
            return clazz.getPackage().getName() + "." + getTag(clazz);
        }
    }

}
