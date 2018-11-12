package shellhub.github.neteasemusic.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

@Data
@Entity(tableName = "search_history")
public class SearchHistory {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "keyword")
    private String keyword;

    public SearchHistory() {
    }

    public SearchHistory(int id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }
}
