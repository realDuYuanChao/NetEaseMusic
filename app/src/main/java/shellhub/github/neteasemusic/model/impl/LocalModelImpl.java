package shellhub.github.neteasemusic.model.impl;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import com.blankj.utilcode.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.LocalModel;
import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.TagUtils;

public class LocalModelImpl implements LocalModel {
    private String TAG = TagUtils.getTag(this.getClass());
    ArrayList<Single> singles = new ArrayList<>();

    @Override
    public void loadSong(Single single, Callback callback) {
        callback.onLoadedSong(single.getData());

        //store song album
        LogUtils.d(TAG, single.getAlbumId());
        LogUtils.d(TAG, getAlbumArtUri(single.getAlbumId()).toString());
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY,
                getAlbumArtUri(single.getAlbumId()).toString());
    }

    @Override
    public void loadAllSingle(Callback callback) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = Utils.getApp().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            Single single = new Single();
            EventBus.getDefault().post(single);
            single.setData(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            single.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            single.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
            single.setAlbumId(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)));
            if (!singles.contains(single)) {
                singles.add(single);
            }
        }
        callback.onLoadedAllSingle(singles);
    }

    @Override
    public void loadAllAlbum(Callback callback) {
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String[] projects = new String[]{"_id",MediaStore.Audio.AlbumColumns.ALBUM, MediaStore.Audio.AlbumColumns.ARTIST, "artist_id", MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS, MediaStore.Audio.AlbumColumns.FIRST_YEAR};
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

        callback.onLoadedAllAlbum(albums);
    }

    @Override
    public void loadAllArtist(Callback callback) {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = MediaStore.Audio.Artists.ARTIST + " ASC";
        Cursor cursor = Utils.getApp().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            Artist artist = new Artist();
            String artistName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST)).split("/")[0];
            artist.setName(artistName);
            artist.setSongCount(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)));
            artists.add(artist);

            artist.setProfile(""/**TODO**/);

        }
        LogUtils.d(TAG, artists);
        callback.onLoadAllArtist(artists);
    }

    @Override
    public void query(String keyword, Callback callback) {
        keyword = keyword.trim();
        if (keyword.equals("")) {
            callback.onLoadedAllSingle(singles);
            return;
        }
        List<Single> querySingles = new ArrayList<>();
        for (Single single : singles) {
            if (single.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                querySingles.add(single);
            }
        }
        LogUtils.d(TAG, querySingles);
        callback.onLoadedAllSingle(querySingles);
    }

    public static Uri getAlbumArtUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }
}
