package shellhub.github.neteasemusic.model;

import java.util.List;

public interface LocalCallback<T> {
    void load(List<T> datas);
}
