package shellhub.github.neteasemusic.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.florent37.glidepalette.GlidePalette;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import shellhub.github.neteasemusic.BaseApp;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.PlaylistDetailAdapter;
import shellhub.github.neteasemusic.presenter.PlaylistPresenter;
import shellhub.github.neteasemusic.presenter.impl.PlaylistPresenterImpl;
import shellhub.github.neteasemusic.response.playlist.Playlist;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongItem;
import shellhub.github.neteasemusic.util.ConstantUtils;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.PlaylistDetailView;

public class PlaylistDetailActivity extends BaseApp implements PlaylistDetailView {

    @BindView(R.id.rv_playlist_detail)
    RecyclerView rvPlaylistDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.iv_playlist_cover)
    ImageView ivPlaylistCover;

    @BindView(R.id.tv_playlist_name)
    TextView tvPlaylistName;

    @BindView(R.id.tv_creator_name)
    TextView tvCreatorName;

    @BindView(R.id.ivPlaylistCreator)
    CircleImageView ivPlaylistCreator;

    @BindView(R.id.tv_playlist_comment)
    TextView tvPlaylistComment;

    @BindView(R.id.tv_playlist_share)
    TextView tvPlaylistShare;

    @BindView(R.id.tv_playlist_download)
    TextView tvPlaylistDownload;

    @BindView(R.id.tv_playlist_multi_pick)
    TextView tvPlaylistMultiPick;

    PlaylistDetailAdapter adapter;
    RecommendSongItem recommendSongItem;
    private String TAG = TagUtils.getTag(this.getClass());
    private PlaylistPresenter mPlaylistPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_playlist_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent().getSerializableExtra(ConstantUtils.RECOMMEND_PLAYLIST_KEY) instanceof RecommendSongItem) {
            recommendSongItem = (RecommendSongItem) getIntent().getSerializableExtra(ConstantUtils.RECOMMEND_PLAYLIST_KEY);
        }
        setUpMVP();
    }

    private void initCollapsingToolbar() {
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.setExpanded(true);
        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(recommendSongItem.getName());
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    toolbar.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    toolbar.setVisibility(View.GONE);
                    collapsingToolbarLayout.setTitle(" ");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void showPlaylist(Playlist playlist) {
        adapter.setTracksItems(playlist.getTracks());
        adapter.notifyDataSetChanged();
        //todo


        tvPlaylistComment.setText(playlist.getCommentCount() + "");
        tvPlaylistShare.setText(playlist.getShareCount() + "");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showPlaylistCover(String coverImgUrl) {
        Glide.with(this).load(coverImgUrl)
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(20)))
                .listener(GlidePalette.with(coverImgUrl)
                .use(GlidePalette.Profile.MUTED_DARK)
                .intoBackground(collapsingToolbarLayout))
                .into(ivPlaylistCover);

    }

    @Override
    public void setUpMVP() {
        rvPlaylistDetail.setLayoutManager(new LinearLayoutManager(this));
        rvPlaylistDetail.setAdapter(adapter = new PlaylistDetailAdapter());
        mPlaylistPresenter = new PlaylistPresenterImpl(this);
        mPlaylistPresenter.getPlaylist(recommendSongItem);//todo

        showPlaylistCover(recommendSongItem.getPicUrl());

        tvPlaylistName.setText(recommendSongItem.getName());
        tvCreatorName.setText(recommendSongItem.getCreator().getNickname());
        Glide.with(this).load(recommendSongItem.getCreator().getAvatarUrl()).into(ivPlaylistCreator);

    }
}
