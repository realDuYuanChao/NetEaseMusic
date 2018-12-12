package shellhub.github.neteasemusic.model.entities;

import java.util.List;

import lombok.Data;
import shellhub.github.neteasemusic.response.banner.BannersItem;

@Data
public class BannerEvent {
    List<BannersItem> bannersItems;

    public BannerEvent(List<BannersItem> bannersItems) {
        this.bannersItems = bannersItems;
    }
}
