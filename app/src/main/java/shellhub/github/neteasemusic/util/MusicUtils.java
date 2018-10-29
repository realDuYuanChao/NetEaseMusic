package shellhub.github.neteasemusic.util;

import android.database.Cursor;
import android.provider.MediaStore;

import com.blankj.utilcode.util.Utils;

public class MusicUtils {
    public static int getCount() {
        Cursor cursor = Utils.getApp().getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, MediaStore.Audio.Media.IS_MUSIC, null, null);
        int count = 0;
        while (cursor.moveToNext()) {
            count++;
        }

        return count;
    }
}
