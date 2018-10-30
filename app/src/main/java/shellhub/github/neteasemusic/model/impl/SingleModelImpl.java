package shellhub.github.neteasemusic.model.impl;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;

import shellhub.github.neteasemusic.model.SingleModel;
import shellhub.github.neteasemusic.model.entities.Single;

public class SingleModelImpl implements SingleModel {
    @Override
    public void loadSingle(Callback callback) {
        ArrayList<Single> singles = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = Utils.getApp().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            Single single = new Single();
            single.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            single.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
            singles.add(single);
        }
        callback.loadSingle(singles);
    }
}
