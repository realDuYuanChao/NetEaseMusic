package shellhub.github.neteasemusic.model.entities.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Observable;
import shellhub.github.neteasemusic.model.entities.SearchHistory;

@Dao
public interface SearchHistoryDao {
    @Query("SELECT * FROM search_history")
    Observable<List<SearchHistory>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SearchHistory... histories);

    @Delete()
    void delete(SearchHistory history);

    @Query("DELETE  FROM search_history")
    void deleteAll();
}
