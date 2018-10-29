package shellhub.github.neteasemusic.model.entities;

import java.util.List;

import lombok.Data;

@Data
public class SingleEvent {
    private List<Single> singles;

    public SingleEvent(List<Single> singles) {
        this.singles = singles;
    }
}
