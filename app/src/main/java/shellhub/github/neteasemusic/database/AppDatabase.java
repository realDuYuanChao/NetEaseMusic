package shellhub.github.neteasemusic.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import shellhub.github.neteasemusic.model.entities.SearchHistory;

@Database(entities = {SearchHistory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

}
