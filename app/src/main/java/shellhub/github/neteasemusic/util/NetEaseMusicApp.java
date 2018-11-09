package shellhub.github.neteasemusic.util;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import shellhub.github.neteasemusic.service.impl.MusicServiceImpl;

public class NetEaseMusicApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, MusicServiceImpl.class));
    }
}
