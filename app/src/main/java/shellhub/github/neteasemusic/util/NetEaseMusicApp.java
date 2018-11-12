package shellhub.github.neteasemusic.util;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.blankj.utilcode.util.Utils;

import androidx.room.Room;
import shellhub.github.neteasemusic.database.AppDatabase;
import shellhub.github.neteasemusic.networking.NetEaseMusicService;
import shellhub.github.neteasemusic.service.impl.MusicServiceImpl;

public class NetEaseMusicApp extends Application {
    private static final String DB_NAME = "NeEaseMusic.db";
    private static AppDatabase appDatabase;
    private static NetEaseMusicService netEaseMusicService;

    public static void setNetEaseMusicService(NetEaseMusicService netEaseMusicService) {
        NetEaseMusicApp.netEaseMusicService = netEaseMusicService;
    }

    public static NetEaseMusicService getNetEaseMusicService() {
        return netEaseMusicService;
    }

    public static AppDatabase getDBInstance() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, MusicServiceImpl.class));

        new Thread(()->{
            appDatabase = Room.databaseBuilder(Utils.getApp(), AppDatabase.class, DB_NAME).build();
        }).start();
    }
}
