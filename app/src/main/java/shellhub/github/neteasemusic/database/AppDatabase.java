package shellhub.github.neteasemusic.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import shellhub.github.neteasemusic.model.entities.SearchHistory;
import shellhub.github.neteasemusic.model.entities.dao.SearchHistoryDao;

@Database(entities = {SearchHistory.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchHistoryDao searchHistoryDao();
}
