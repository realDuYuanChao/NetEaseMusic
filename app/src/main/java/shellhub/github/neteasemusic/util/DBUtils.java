package shellhub.github.neteasemusic.util;

import com.blankj.utilcode.util.Utils;

import androidx.room.Room;
import shellhub.github.neteasemusic.database.AppDatabase;

public class DBUtils {
    private static AppDatabase DB_SINGLE_INSTANCE = null;
    private static final String DB_NAME = "NeEaseMusic.db";

    public static AppDatabase getInstance() {
        if (DB_SINGLE_INSTANCE == null) {
            DB_SINGLE_INSTANCE = Room.databaseBuilder(Utils.getApp(), AppDatabase.class, DB_NAME).build();
            return DB_SINGLE_INSTANCE;
        }
        return DB_SINGLE_INSTANCE;
    }
}
