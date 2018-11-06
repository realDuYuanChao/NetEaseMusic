package shellhub.github.neteasemusic.model.impl;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.AlbumModel;
import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.util.TagUtils;

import static android.provider.MediaStore.Audio.AlbumColumns;

public class AlbumModelImpl implements AlbumModel {
    private String TAG = TagUtils.getTag(this.getClass());
    @Override
    public void loadAlbums(Callback callback) {
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String[] projects = new String[]{"_id",AlbumColumns.ALBUM, AlbumColumns.ARTIST, "artist_id", AlbumColumns.NUMBER_OF_SONGS, AlbumColumns.FIRST_YEAR};
        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = Utils.getApp().getContentResolver().query(uri, projects, selection, selectionArgs, null);

        List<Album> albums = new ArrayList<>();
        while (cursor.moveToNext()) {
            Album album = new Album();
            album.setId(cursor.getInt(0));
            album.setAlbumCoverUri(getAlbumArtUri(album.getId()));
            album.setTitle(cursor.getString(1));
            album.setArtistName(cursor.getString(2));
            album.setArtistId(cursor.getInt(3));
            album.setSongCount(cursor.getInt(4));
            album.setYear(cursor.getInt(5));

            albums.add(album);
        }
        LogUtils.d(TAG, albums);

        callback.onLoaded(albums);
    }

    public static Uri getAlbumArtUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }
}
