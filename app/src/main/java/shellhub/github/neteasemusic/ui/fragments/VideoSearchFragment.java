package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.SearchVideoAdapter;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;
import shellhub.github.neteasemusic.util.TagUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoSearchFragment extends Fragment {
    private String TAG = TagUtils.getTag(this.getClass());
    @BindView(R.id.rv_video)
    RecyclerView rvVideo;

    private SearchVideoAdapter adapter;

    public VideoSearchFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final VideoSearchFragment INSTANCE = new VideoSearchFragment();
    }

    public static VideoSearchFragment getInstance() {
        return VideoSearchFragment.SingletonHelper.INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_search, container, false);
        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setUp();
        return view;
    }

    private void setUp(){
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvVideo.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        if (adapter == null) {
            adapter = new SearchVideoAdapter();
        }
        rvVideo.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchVideoEvent(VideoResponse videoResponse) {
        LogUtils.d(TAG, videoResponse);
        adapter.setVideosItems(videoResponse.getResult().getVideos());
        adapter.notifyDataSetChanged();
    };

}
