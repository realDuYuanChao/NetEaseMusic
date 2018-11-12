package shellhub.github.neteasemusic.service.impl;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.model.entities.NotificationElement;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.impl.SingleModelImpl;
import shellhub.github.neteasemusic.service.MusicService;
import shellhub.github.neteasemusic.ui.activities.PlayActivity;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.MusicUtils;
import shellhub.github.neteasemusic.util.TagUtils;

import static android.app.Notification.CATEGORY_SERVICE;
import static androidx.core.app.NotificationCompat.PRIORITY_MIN;

public class MusicServiceImpl extends Service implements MusicService,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener,
        AudioManager.OnAudioFocusChangeListener {

    private final IBinder mIBinder = new MusicBinder();
    private String TAG = TagUtils.getTag(this.getClass());
    private static MediaPlayer mPlayer;
    private String mMusicUrl;
    private int resumePosition;
    private AudioManager audioManager;

    private List<Single> localSingles = new ArrayList<>();
    private List<Single> networkMusics = new ArrayList<>();
    private boolean mNetworkMedia;

    private int mBufferPercent = 0;

    private int NOTIFICATION_ID = 101;

    public MusicServiceImpl() {
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        LogUtils.d(TAG, "completed");
        mPlayer.seekTo(0);
        mPlayer.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        new Thread(this::playMedia);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //Invoked when there has been an error during an asynchronous operation
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.d("MediaPlayer Error", "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.d("MediaPlayer Error", "MEDIA ERROR SERVER DIED " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.d("MediaPlayer Error", "MEDIA ERROR UNKNOWN " + extra);
                break;
        }
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        mBufferPercent = percent;
    }


    @Override
    public void onAudioFocusChange(int focusChange) {
        //Invoked when the audio focus of the system is updated.
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // resume playback
                if (mPlayer == null) initMediaPlayer();
                else if (!mPlayer.isPlaying()) mPlayer.start();
                mPlayer.setVolume(1.0f, 1.0f);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                // Lost focus for an unbounded amount of time: stop playback and release media player
                if (mPlayer.isPlaying()) mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // Lost focus for a short time, but we have to stop
                // playback. We don't release the media player because playback
                // is likely to resume
                if (mPlayer.isPlaying()) mPlayer.pause();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
                if (mPlayer.isPlaying()) mPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        registerBecomingNoisyReceiver();
        registerPlayerReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        startForegroundService();
//
//        try {
//            //An audio file is passed to the service through putExtra();
//            mMusicUrl = intent.getExtras().getString("media");
//        } catch (NullPointerException e) {
//            stopSelf();
//        }
//
//        //Request audio focus
//        if (requestAudioFocus() == false) {
//            //Could not gain focus
//            stopSelf();
//        }
//
//        if (mMusicUrl != null && mMusicUrl != "")
//            initMediaPlayer();

        new Thread(() -> {
            new SingleModelImpl().loadSingle(singles -> localSingles = singles);
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }


    public void startForegroundService() {
        Log.d(TAG, "startForegroundService: ");
        startForeground(NOTIFICATION_ID, buildNotification(null));
    }

    private Notification buildNotification(NotificationElement notificationElement) {
        Intent notificationIntent = new Intent(this, PlayActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        RemoteViews controllerLayout = new RemoteViews(getPackageName(), R.layout.control_notification);

        //play or pause
        Intent playPauseIntent = new Intent(this, ControllerListener.class);
        playPauseIntent.setAction(ConstantUtils.ACTION_STATUS);
        PendingIntent controllerPendingIntent = PendingIntent.getBroadcast(this, 0, playPauseIntent, 0);
        controllerLayout.setOnClickPendingIntent(R.id.iv_control_play_pause, controllerPendingIntent);

        //previous
        Intent previousIntent = new Intent(this, ControllerListener.class);
        playPauseIntent.setAction(ConstantUtils.ACTION_PREVIOUS);
        PendingIntent PreviousPendingIntent = PendingIntent.getBroadcast(this, 0, previousIntent, 0);
        controllerLayout.setOnClickPendingIntent(R.id.iv_control_previous, PreviousPendingIntent);

        //next
        Intent nextIntent = new Intent(this, ControllerListener.class);
        playPauseIntent.setAction(ConstantUtils.ACTION_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 0, nextIntent, 0);
        controllerLayout.setOnClickPendingIntent(R.id.iv_control_next, nextPendingIntent);

        if (notificationElement != null) {
            controllerLayout.setImageViewBitmap(R.id.iv_song_ablum, notificationElement.getSongAlbumBitmap());
            controllerLayout.setTextViewText(R.id.tv_controller_name, notificationElement.getSongName());
            controllerLayout.setTextViewText(R.id.tv_controller_artist_name, notificationElement.getSongArtistAndTitle());
            if (notificationElement.isLoved()) {
                controllerLayout.setImageViewResource(R.id.iv_control_fav, R.drawable.note_btn_loved);
            } else {
                controllerLayout.setImageViewResource(R.id.iv_control_fav, R.drawable.note_btn_love_white);
            }

            if (notificationElement.isPlaying()) {
                controllerLayout.setImageViewResource(R.id.iv_control_play_pause, R.drawable.note_btn_pause_white);
            } else {
                controllerLayout.setImageViewResource(R.id.iv_control_play_pause, R.drawable.note_btn_play_white);
            }

            if (notificationElement.isOpenLyric()) {
                controllerLayout.setImageViewResource(R.id.iv_control_lyric, R.drawable.note_btn_lyced);
            } else {
                controllerLayout.setImageViewResource(R.id.iv_control_lyric, R.drawable.note_btn_lyc_white);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = createNotificationChannel("my service", "my background service");
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);

            return builder.setOngoing(true)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_small_logo)
                    .setContent(controllerLayout)
                    .setPriority(PRIORITY_MIN)
                    .setCategory(CATEGORY_SERVICE)
                    .build();
        } else {
            return null;
            //TODO
        }
    }

    public static class ControllerListener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ConstantUtils.ACTION_STATUS:
                    if (SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getBoolean(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY)) {
                        Utils.getApp().sendBroadcast(new Intent(ConstantUtils.ACTION_PAUSE));
                    }else {
                        Utils.getApp().sendBroadcast(new Intent(ConstantUtils.ACTION_PLAY));
                    }
                case ConstantUtils.ACTION_PREVIOUS:
                    break;
                case ConstantUtils.ACTION_NEXT:
                    break;
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId, String channelName) {
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        return channelId;
    }

    private void updateNotification(NotificationElement notificationElement) {
        startForeground(NOTIFICATION_ID, buildNotification(notificationElement));
    }

    @Override
    public void onDestroy() {
//        removeAudioFocus();todo
        if (mPlayer != null) {
            stopMedia();
            mPlayer.release();
        }
        unregisterReceiver(playerReceiver);
        unregisterReceiver(becomingNoisyReceiver);
        stopSelf();
        super.onDestroy();
    }

    @Override
    public int getCurrentPosition() {
        if (mPlayer != null) {
            return mPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    @Override
    public int getDuration() {
        if (mPlayer != null) {
            return mPlayer.getDuration();
        } else {
            return 0;
        }
    }

    @Override
    public void seekTo(int position) {
        LogUtils.d(TAG, position);
        resumePosition = position;
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
        resumeMedia();
    }

    @Override
    public Single next() {
        switch (SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING).getInt(ConstantUtils.SP_PLAY_TYPE_KEY, 0)) {
            case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                for (int i = 0; i < localSingles.size(); i++) {
                    if (localSingles.get(i).getData().equals(mMusicUrl)) {
                        if (i == localSingles.size() - 1) {
                            return localSingles.get(0);
                        } else {
                            return localSingles.get(i + 1);
                        }
                    }
                }
                break;
            case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                for (Single single : localSingles) {
                    if (single.getData().equals(mMusicUrl)) {
                        //this is slow
                        //TODO
                        return single;
                    }
                }
                break;
            case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                return localSingles.get(new Random().nextInt(localSingles.size()));
        }
        return null;
    }


    @Override
    public Single previous() {
        switch (SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING).getInt(ConstantUtils.SP_PLAY_TYPE_KEY, 0)) {
            case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                for (int i = 0; i < localSingles.size(); i++) {
                    if (localSingles.get(i).getData().equals(mMusicUrl)) {
                        if (i == 0) {
                            return localSingles.get(localSingles.size() - 1);
                        } else {
                            return localSingles.get(i - 1);
                        }
                    }
                }
                break;
            case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                for (Single single : localSingles) {
                    if (single.getData().equals(mMusicUrl)) {
                        //this is slow
                        //TODO
                        return single;
                    }
                }
                break;
            case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                return localSingles.get(new Random().nextInt(localSingles.size()));
        }
        return null;
    }

    @Override
    public int getBufferPercent() {
        return mBufferPercent;
    }

    public class MusicBinder extends Binder {
        public MusicServiceImpl getMusicService() {
            // Return this instance of LocalService so clients can call public methods
            return MusicServiceImpl.this;
        }
    }

    private void initMediaPlayer() {
        mPlayer = new MediaPlayer();
        //Set up MediaPlayer event listeners
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnBufferingUpdateListener(this);
        mPlayer.setOnSeekCompleteListener(this);
        mPlayer.setOnInfoListener(this);
        //Reset so that the MediaPlayer is not pointing to another data source
        mPlayer.reset();

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            // Set the data source to the mediaFile location
            mPlayer.setDataSource(mMusicUrl);
        } catch (IOException e) {
            e.printStackTrace();
//            stopSelf();

            //if network error, try again
            initMediaPlayer();
        }
        mPlayer.prepareAsync();
    }

    private void playMedia() {
        if (!mPlayer.isPlaying()) {
            LogUtils.d(TAG, "start");
            mPlayer.start();
        }
    }

    private void stopMedia() {
        if (mPlayer == null) return;
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
    }

    private void pauseMedia() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            resumePosition = mPlayer.getCurrentPosition();
        }
    }

    private void resumeMedia() {
        if (!mPlayer.isPlaying()) {
            mPlayer.seekTo(resumePosition);
            mPlayer.start();
        }
    }

    private boolean requestAudioFocus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Focus gained
            return true;
        }
        //Could not gain focus
        return false;
    }

    private boolean removeAudioFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                audioManager.abandonAudioFocus(this);
    }

    private BroadcastReceiver becomingNoisyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            pauseMedia();
//            buildNotification(PlaybackStatus.PAUSED);
            //TODO

        }
    };

    private void registerBecomingNoisyReceiver() {
        //register after getting audio focus
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        registerReceiver(becomingNoisyReceiver, intentFilter);
    }

    private BroadcastReceiver playerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ConstantUtils.ACTION_PREVIOUS:
                    mMusicUrl = previous().getData();
                    stopMedia();
                    LogUtils.d(TAG, "NEW MUSIC");
                    initMediaPlayer();
                    break;
                case ConstantUtils.ACTION_PLAY:
                    LogUtils.d(TAG, "ACTION_PLAY");
                    String newMediaUrl = intent.getStringExtra("media");
                    if (!StringUtils.isEmpty(newMediaUrl) && !newMediaUrl.equals(mMusicUrl)) {
                        stopMedia();
                        LogUtils.d(TAG, "NEW MUSIC");
                        mMusicUrl = newMediaUrl;
                        initMediaPlayer();
                        break;
                    }
                    playMedia();
                    SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY, true);
                    break;
                case ConstantUtils.ACTION_PAUSE:
                    pauseMedia();
                    SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).put(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY, false);
                    break;
                case ConstantUtils.ACTION_NEXT:
                    mMusicUrl = next().getData();
                    stopMedia();
                    LogUtils.d(TAG, "NEW MUSIC");
                    initMediaPlayer();
                    break;
            }
            LogUtils.d(TAG, intent.getAction());

            new Thread(() -> {
                NotificationElement notificationElement = new NotificationElement();
                notificationElement.setSongName(SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS).getString(ConstantUtils.SP_CURRENT_SONG_NAME_KEY, "unknow name"));
                notificationElement.setSongArtistAndTitle(SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS).getString(ConstantUtils.SP_CURRENT_SONG_ARTIST_AND_NAME, "unknow artist and title"));
                notificationElement.setSongAlbumBitmap(MusicUtils.getBitmap(SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS).getString(ConstantUtils.SP_CURRENT_SONG_ALBUM_URL_KEY)));

                notificationElement.setPlaying(SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_STATUS, Context.MODE_PRIVATE).getBoolean(ConstantUtils.SP_CURRENT_IS_PLAYING_STATUS_KEY, false));
                notificationElement.setOpenLyric(true); //TODO
                updateNotification(notificationElement);
            }).start();
        }
    };

    private void registerPlayerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConstantUtils.ACTION_PREVIOUS);
        intentFilter.addAction(ConstantUtils.ACTION_PLAY);
        intentFilter.addAction(ConstantUtils.ACTION_PAUSE);
        intentFilter.addAction(ConstantUtils.ACTION_NEXT);
        registerReceiver(playerReceiver, intentFilter);
    }
}
