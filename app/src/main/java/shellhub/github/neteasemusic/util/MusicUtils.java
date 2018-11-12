package shellhub.github.neteasemusic.util;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import com.blankj.utilcode.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.StringJoiner;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import retrofit2.Retrofit;
import shellhub.github.neteasemusic.model.impl.PlayModelIml;
import shellhub.github.neteasemusic.networking.NetEaseMusicAPI;
import shellhub.github.neteasemusic.response.search.ArtistsItem;
import shellhub.github.neteasemusic.response.search.SongsItem;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;
import shellhub.github.neteasemusic.service.MusicService;

public class MusicUtils {
    public static int getCount() {
        Cursor cursor = Utils.getApp().getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, MediaStore.Audio.Media.IS_MUSIC, null, null);
        int count = 0;
        while (cursor.moveToNext()) {
            count++;
        }

        return count;
    }

    public static String formatDuration(long duration) {
        long hours = duration / ConstantUtils.ONE_HOUR;
        long minutes = (duration - hours * ConstantUtils.ONE_HOUR) / ConstantUtils.ONE_MINUTE;
        long seconds = (duration - hours * ConstantUtils.ONE_HOUR - minutes * ConstantUtils.ONE_MINUTE) / ConstantUtils.ONE_SECOND;

        StringBuilder builder = new StringBuilder();
        if (hours != 0) {
            if (hours < 10) {
                builder.append("0").append(hours);
            }else {
                builder.append(hours);
                builder.append(hours);
            }
            builder.append(":");
        }
        if (minutes < 10) {
            builder.append("0").append(minutes);
        }else {
            builder.append(minutes);
        }
        builder.append(":");

        if (seconds < 10) {
            builder.append("0").append(seconds);
        }else {
            builder.append(seconds);
        }
        return builder.toString();
    }

    public static String getArtistAndAlbum(SongsItem songsItem) {
        StringJoiner joiner = new StringJoiner("/");
        for (ArtistsItem artist : songsItem.getArtists()) {
            joiner.add(artist.getName());
        }

        return joiner.toString() + " - " + songsItem.getAlbum().getName();
    }

    public static Bitmap getBitmap(String url) {
        try {
            return BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getBitmap(int songId) {
        return null;
    }
}
