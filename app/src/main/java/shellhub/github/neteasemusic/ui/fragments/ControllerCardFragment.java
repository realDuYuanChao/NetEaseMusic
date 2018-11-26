package shellhub.github.neteasemusic.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.util.TagUtils;

public class ControllerCardFragment extends Fragment {

    private String TAG = TagUtils.getTag(this.getClass());

    @BindView(R.id.iv_controller_album_cover)
    ImageView ivControllerAlbumCover;

    @BindView(R.id.iv_controller_play_pause)
    ImageView ivCOntrollerPlayPause;

    @BindView(R.id.iv_controller_playlist)
    ImageView ivCOntrollerPlaylist;

    @BindView(R.id.tv_controller_lyric)
    TextView tvControllerLyric;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_nowplaying_card, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.iv_controller_play_pause})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_controller_play_pause:
                LogUtils.d(TAG, "click play pause controller");
                break;
        }
    }
}
