package shellhub.github.neteasemusic.model.entities.dao;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import shellhub.github.neteasemusic.model.entities.SearchHistory;

public interface SearchHistoryDao {
    @Query("SELECT * FROM search_history")
    List<SearchHistory> getAll();

    @Insert
    void insertAll(SearchHistory... histories);

    @Delete()
    void delete(SearchHistory history);
}
