package shellhub.github.neteasemusic.ui.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.presenter.PlayPresenter;
import shellhub.github.neteasemusic.presenter.impl.PlayPresenterImpl;
import shellhub.github.neteasemusic.response.mp3.SongResponse;
import shellhub.github.neteasemusic.service.MusicService;
import shellhub.github.neteasemusic.service.impl.MusicServiceImpl;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.MusicUtils;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.PlayView;

public class PlayActivity extends AppCompatActivity implements PlayView, ServiceConnection, SeekBar.OnSeekBarChangeListener {
    private String TAG = TagUtils.getTag(this.getClass());

    @BindView(R.id.iv_play_type)
    ImageView ivPlayType;

    @BindView(R.id.iv_previous)
    ImageView ivPrevious;

    @BindView(R.id.iv_play_pause)
    ImageView ivPlayPause;

    @BindView(R.id.iv_next)
    ImageView ivNext;

    @BindView(R.id.iv_playlist)
    ImageView ivPlaylist;

    @BindView(R.id.iv_favorite)
    ImageView ivFavorite;

    @BindView(R.id.iv_download)
    ImageView ivDownload;

    @BindView(R.id.iv_comment)
    ImageView ivComment;

    @BindView(R.id.sb_duration)
    SeekBar sbDuration;

    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;

    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;

    private PlayPresenter mPlayPresenter;
    private Handler mHandler = new Handler();

    private Runnable runnable  = new Runnable() {
        @Override
        public void run () {
            if (mBound) {
                sbDuration.setMax(mMusicService.getDuration() / 1000);
                int mCurrentPosition = mMusicService.getCurrentPosition() / 1000;
                sbDuration.setProgress(mCurrentPosition);
                tvCurrentTime.setText(MusicUtils.formatDuration(mMusicService.getCurrentPosition()));
                tvTotalTime.setText(MusicUtils.formatDuration(mMusicService.getDuration()));
                mHandler.postDelayed(this, 1000);
            }
        }
    };
    private String mMediaUrl;
    private int songId;

    private MusicService mMusicService;
    private boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        sbDuration.setOnSeekBarChangeListener(this);
        initPlayTypeIcon();
        setUpMVP();

        SongResponse songResponse = (SongResponse) getIntent().getSerializableExtra(ConstantUtils.MUSIC_URI_KEY);
        if (songResponse != null) {
            songId = songResponse.getData().get(0).getId();
            LogUtils.d(TAG, songResponse.getData().get(0).getUrl());
            mMediaUrl = songResponse.getData().get(0).getUrl();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicServiceImpl.class);
        intent.putExtra("media", mMediaUrl);
        if (!mBound) {
            bindService(intent, this, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(TAG, "onStop");
        mHandler.removeCallbacks(runnable);
        unbindService(this);
        mBound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                LogUtils.d(TAG, item.getTitle());
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        LogUtils.d(TAG, "onServiceConnected");
        mBound = true;
        MusicServiceImpl.MusicBinder binder = (MusicServiceImpl.MusicBinder) service;
        mMusicService = binder.getMusicService();
        if (!TextUtils.isEmpty(mMediaUrl)) {
            playAudio(mMediaUrl);
        }
        updateDuration();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        LogUtils.d(TAG, "onServiceDisconnected");
        mBound = false;
    }

    private void playAudio(String media) {
        Intent intent = new Intent(ConstantUtils.ACTION_PLAY);
        intent.putExtra("media", media);
        sendBroadcast(intent);
        updateDuration();
    }

    @OnClick({R.id.iv_play_type, R.id.iv_menu, R.id.iv_favorite,
            R.id.iv_download, R.id.iv_comment, R.id.iv_playlist,
            R.id.iv_previous, R.id.iv_play_pause, R.id.iv_next})
    public void onClick(View view) {
        LogUtils.d(TAG, "onClick");
        mPlayPresenter.executeClick(view);
    }

    @Override
    public void playType(int resId) {
        Glide.with(this).load(resId).into(ivPlayType);
    }

    @Override
    public void previous() {
        sendBroadcast(new Intent(ConstantUtils.ACTION_PREVIOUS));
    }

    @Override
    public void play() {
        Glide.with(this).load(R.drawable.note_btn_pause_white).into(ivPlayPause);
        sendBroadcast(new Intent(ConstantUtils.ACTION_PLAY));
    }

    @Override
    public void pause() {
        Glide.with(this).load(R.drawable.note_btn_play_white).into(ivPlayPause);
        sendBroadcast(new Intent(ConstantUtils.ACTION_PAUSE));
    }

    @Override
    public void next() {
        sendBroadcast(new Intent(ConstantUtils.ACTION_NEXT));
    }

    @Override
    public void playlist() {

    }

    @Override
    public void favorite() {

    }

    @Override
    public void download() {

    }

    @Override
    public void comment() {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(ConstantUtils.MUSIC_ID_KEY, songId);
        startActivity(intent);
    }

    @Override
    public void menu() {

    }


    @Override
    public void updateDuration() {
        mHandler.post(runnable);
    }

    @Override
    public void initPlayTypeIcon() {
        switch (SPUtils.getInstance(ConstantUtils.SP_NET_EASE_MUSIC_SETTING).getInt(ConstantUtils.SP_PLAY_TYPE_KEY, 0)) {
            case ConstantUtils.PLAY_MODE_LOOP_ALL_CODE:
                Glide.with(this).load(R.drawable.ic_loop_all_black).into(ivPlayType);
                break;
            case ConstantUtils.PLAY_MODE_LOOP_SINGLE_CODE:
                Glide.with(this).load(R.drawable.loop_single_black).into(ivPlayType);
                break;
            case ConstantUtils.PLAY_MODE_SHUFFLE_CODE:
                Glide.with(this).load(R.drawable.shuffle_black).into(ivPlayType);
                break;
        }
    }

    @Override
    public void setUpMVP() {
        mPlayPresenter = new PlayPresenterImpl(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        LogUtils.d(TAG, "onProgressChanged");
        tvCurrentTime.setText(MusicUtils.formatDuration(progress * 1000));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        LogUtils.d(TAG, "onStartTrackingTouch");
        mHandler.removeCallbacks(runnable);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        LogUtils.d(TAG, "onStopTrackingTouch");
        mHandler.post(runnable);
        mMusicService.seekTo(seekBar.getProgress() * 1000);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
