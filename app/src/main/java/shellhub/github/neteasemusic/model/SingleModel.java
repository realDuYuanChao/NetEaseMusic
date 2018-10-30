package shellhub.github.neteasemusic.model;

import java.util.List;

import shellhub.github.neteasemusic.model.entities.Single;

public interface SingleModel {
    void loadSingle(Callback callback);

    interface Callback{
        void loadSingle(List<Single> singles);
    }
}
