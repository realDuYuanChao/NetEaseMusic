package shellhub.github.neteasemusic.model.entities;

import java.util.List;

import lombok.Data;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;

@Data
public class RecommendSongItemEvent {
    List<RecommendSongItem> recommendSongItems;
}
