package shellhub.github.neteasemusic.model.impl;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;

import shellhub.github.neteasemusic.model.ArtistModel;
import shellhub.github.neteasemusic.model.entities.Artist;
import shellhub.github.neteasemusic.util.TagUtils;

public class ArtistModelImpl implements ArtistModel {

    private String TAG = TagUtils.getTag(this.getClass());
    @Override
    public void loadArtist(Callback callback) {
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
        callback.loadArtist(artists);
    }
}
