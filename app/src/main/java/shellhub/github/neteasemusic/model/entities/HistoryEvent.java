package shellhub.github.neteasemusic.model.entities;

import lombok.Data;

@Data
public class HistoryEvent {
    private String keyword;

    public HistoryEvent(String keyword) {
        this.keyword = keyword;
    }
}
