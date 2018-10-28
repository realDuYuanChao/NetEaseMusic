package shellhub.github.neteasemusic.adapter;

public interface OnClickListener<T> {
    void onItemClick(T item, int index);

    void onLongItemClick(T item, int index);
}
