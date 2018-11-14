package shellhub.github.neteasemusic.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
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
        LogUtils.d("formatDuration", duration);
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

    public static <T> T next(List<T> songs) {
        int playlistIndex = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING, Context.MODE_PRIVATE)
                .getInt(ConstantUtils.SP_CURRENT_PLAYLIST_INDEX_KEY, 0);

        switch (SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING, Context.MODE_PRIVATE).getInt(ConstantUtils.SP_PLAY_TYPE_KEY)) {
            case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                if (playlistIndex == songs.size() - 1) { //end of playlist
                    playlistIndex = 0;
                    break;
                }
                playlistIndex++;
                break;
            case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                break;

            case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                playlistIndex = new Random().nextInt(songs.size());
                break;
        }
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_PLAYLIST_INDEX_KEY, playlistIndex);
        return songs.get(playlistIndex); // don't return null
    }

    public static <T> T previous(List<T> songs) {
        int playlistIndex = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING, Context.MODE_PRIVATE)
                .getInt(ConstantUtils.SP_CURRENT_PLAYLIST_INDEX_KEY, 0);

        switch (SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING, Context.MODE_PRIVATE).getInt(ConstantUtils.SP_PLAY_TYPE_KEY)) {
            case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                if (playlistIndex == 0) { //end of playlist
                    playlistIndex = songs.size() - 1;
                    break;
                }
                playlistIndex++;
                break;
            case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                break;

            case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                playlistIndex = new Random().nextInt(songs.size());
                break;
        }
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_PLAYLIST_INDEX_KEY, playlistIndex);
        return songs.get(playlistIndex); // don't return null
    }

}
