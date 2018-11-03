package shellhub.github.neteasemusic.service.impl;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;

import shellhub.github.neteasemusic.service.MusicService;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.TagUtils;

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
    private MediaPlayer mPlayer;
    private String mMusicUrl;
    private int resumePosition;
    private AudioManager audioManager;

    public MusicServiceImpl() {
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        LogUtils.d(TAG, "completed");
        stopMedia();
        stopSelf();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        playMedia();
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
        registerBecomingNoisyReceiver();
        registerPlayerReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            //An audio file is passed to the service through putExtra();
            mMusicUrl = intent.getExtras().getString("media");
        } catch (NullPointerException e) {
            stopSelf();
        }

        //Request audio focus
        if (requestAudioFocus() == false) {
            //Could not gain focus
            stopSelf();
        }

        if (mMusicUrl != null && mMusicUrl != "")
            initMediaPlayer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            stopMedia();
            mPlayer.release();
        }
        removeAudioFocus();
    }

    @Override
    public int getCurrentPosition() {
        return mPlayer.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return mPlayer.getDuration();
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
            stopSelf();
        }
        mPlayer.prepareAsync();
    }

    private void playMedia() {
        if (!mPlayer.isPlaying()) {
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
        if (mPlayer.isPlaying()) {
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

    private BroadcastReceiver becomingNoisyReceiver  = new BroadcastReceiver() {
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
                    break;
                case ConstantUtils.ACTION_PLAY:
                    playMedia();
                    break;
                case ConstantUtils.ACTION_PAUSE:
                    pauseMedia();
                    break;
                case ConstantUtils.ACTION_NEXT:
                    break;
            }
            LogUtils.d(TAG, intent.getAction());
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
