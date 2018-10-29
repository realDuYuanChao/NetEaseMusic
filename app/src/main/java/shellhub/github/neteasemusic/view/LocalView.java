package shellhub.github.neteasemusic.view;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.entities.Single;

public interface LocalView extends BaseView{
    void loadSingle(List<Single> singles);
}
