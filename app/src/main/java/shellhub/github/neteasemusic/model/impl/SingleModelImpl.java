package shellhub.github.neteasemusic.model.impl;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.SingleModel;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.util.TagUtils;

public class SingleModelImpl implements SingleModel {
    private String TAG = TagUtils.getTag(this.getClass());
    ArrayList<Single> singles = new ArrayList<>();

    @Override
    public void loadSingle(Callback callback) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = Utils.getApp().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            Single single = new Single();
            single.setData(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            single.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            single.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
            if (!singles.contains(single)) {
                singles.add(single);
            }
        }
        callback.loadSingle(singles);
    }

    @Override
    public void query(String keyword, Callback callback) {
        keyword = keyword.trim();
        if (keyword.equals("")) {
            callback.loadSingle(singles);
            return;
        }
        List<Single> querySingles = new ArrayList<>();
        for (Single single : singles) {
            if (single.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                querySingles.add(single);
            }
        }
        LogUtils.d(TAG, querySingles);
        callback.loadSingle(querySingles);
    }
}
