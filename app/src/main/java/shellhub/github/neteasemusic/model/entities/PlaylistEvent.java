package shellhub.github.neteasemusic.model.entities;

import lombok.Data;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;

@Data
public class PlaylistEvent {
    RecommendSongItem recommendSongItem;

    public PlaylistEvent(RecommendSongItem recommendSongItem) {
        this.recommendSongItem = recommendSongItem;
    }
}
