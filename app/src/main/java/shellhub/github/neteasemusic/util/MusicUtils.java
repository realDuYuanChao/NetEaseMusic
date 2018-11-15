package shellhub.github.neteasemusic.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.response.search.ArtistsItem;
import shellhub.github.neteasemusic.response.search.SongsItem;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;
import shellhub.github.neteasemusic.vo.NetworkMusic;

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

    public static Bitmap getAlbumCover() {
        String albumUrl = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getString(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY);
        if (albumUrl.contains("http")) {
            //network album
            LogUtils.d("album url" + albumUrl);
            return getBitmap(albumUrl);
        }else {
            //local album
            albumUrl = SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getString(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY);
            LogUtils.d("album url" + albumUrl);
            try {
                return MediaStore.Images.Media.getBitmap(Utils.getApp().getContentResolver(), Uri.parse(albumUrl));
            } catch (IOException e) {
                LogUtils.d("NOT SUCH ALBUM");
                e.printStackTrace();
                return null;//TODO
            }
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
        T item = songs.get(playlistIndex);
        saveSongDetail(item);
        return item;
//        return songs.get(playlistIndex); // don't return null
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
        T item = songs.get(playlistIndex);
        saveSongDetail(item);
        return item;
//        return songs.get(playlistIndex); // don't return null
    }

    public static <T> void saveSongDetail(T item) {
        if (item instanceof NetworkMusic) {
            NetEaseMusicApp.getNetEaseMusicService().getSongDetail(((NetworkMusic) item).getId(), new NetEaseMusicService.Callback<SongDetailResponse>(){
                @Override
                public void onSuccess(SongDetailResponse data) {
                    String albumUrl = data.getSongs().get(0).getAl().getPicUrl();
                    saveAlbumCover(albumUrl);
                    saveSongId(((NetworkMusic) item).getId());
                    saveSongUrl(((NetworkMusic) item).getUrl());
                    saveSongName(((NetworkMusic) item).getName());
                    saveArtistAndAlbum(((NetworkMusic) item).getArtistAndAlbum());
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }else if (item instanceof Single){
            String albumUrl = getAlbumArtUri(((Single) item).getAlbumId()).toString();
            saveAlbumCover(albumUrl);
            saveSongUrl(((Single) item).getData());
            saveSongName(((Single) item).getTitle());
            saveArtistAndAlbum(((Single) item).getArtist() + " - " + ((Single) item).getTitle());
//            saveSongId(((NetworkMusic) item).getId());
//            saveSongUrl(((NetworkMusic) item).getUrl());
//            saveSongName(((NetworkMusic) item).getName());
//            saveArtistAndAlbum(((NetworkMusic) item).getArtistAndAlbum());
        }
    }

    public static Uri getAlbumArtUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }

    public static void saveAlbumCover(String albumCoverUrl) {
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY, albumCoverUrl);
    }

    public static String readAlbumCover() {
        return SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getString(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY);
    }

    public static void saveSongId(int songId) {
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ID_KEY, songId);
    }

    public static int readSongId() {
        return SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getInt(ConstantUtils.SP_CURRENT_SONG_ID_KEY);
    }

    public static void saveSongUrl(String songUrl) {
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_URL_KEY, songUrl);
    }

    public static String readSongUrl() {
        return SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getString(ConstantUtils.SP_CURRENT_SONG_URL_KEY);
    }

    public static void saveSongName(String songName) {
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_NAME_KEY, songName);
    }

    public static String readSongName() {
       return SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getString(ConstantUtils.SP_CURRENT_SONG_NAME_KEY);
    }

    public static void saveArtistAndAlbum(String artistAndAlbum) {
        SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_SONG_ARTIST_AND_ALBUM_KEY, artistAndAlbum);
    }

    public static String readArtistAndAlbum() {
        return SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getString(ConstantUtils.SP_CURRENT_SONG_ARTIST_AND_ALBUM_KEY);
    }
}
