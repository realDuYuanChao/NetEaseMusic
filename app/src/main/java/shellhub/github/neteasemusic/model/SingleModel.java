package shellhub.github.neteasemusic.model;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.entities.Single;

public interface SingleModel {
    void load(Callback callback);

    interface Callback{
        void load(List<Single> singles);
    }
}
