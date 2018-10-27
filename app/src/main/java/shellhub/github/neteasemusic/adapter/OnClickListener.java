package shellhub.github.neteasemusic.adapter;

public interface OnClickListener<T> {
    void onItemClick(T item);

    void onLongItemClick(T item);
}
