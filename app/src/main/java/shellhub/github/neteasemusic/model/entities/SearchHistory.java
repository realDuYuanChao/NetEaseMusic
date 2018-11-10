package shellhub.github.neteasemusic.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_history")
public class SearchHistory {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "keyword")
    public String keyword;
}
