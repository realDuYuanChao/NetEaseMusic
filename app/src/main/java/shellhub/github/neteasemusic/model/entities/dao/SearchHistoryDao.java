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

    @Query("SELECT keyword FROM search_history")
    List<String> getAllKeywords();

    @Query("SELECT * FROM search_history LIMIT 1")
    SearchHistory getTopRow();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SearchHistory... histories);

    @Query("SELECT COUNT(*) FROM search_history")
    int getRows();

    @Query("DELETE FROM search_history WHERE keyword = :keyword")
    void delete(String keyword);

    @Query("DELETE  FROM search_history")
    void deleteAll();
}
