package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.AlbumAdapter;
import shellhub.github.neteasemusic.adapter.SingleAdapter;
import shellhub.github.neteasemusic.model.entities.AlbumEvent;
import shellhub.github.neteasemusic.model.entities.SingleEvent;
import shellhub.github.neteasemusic.util.TagUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {
    private String TAG = TagUtils.getTag(this.getClass());

    @BindView(R.id.rv_albums)
    RecyclerView rvAlbums;

    private AlbumAdapter adapter;

    public AlbumFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final AlbumFragment INSTANCE = new AlbumFragment();
    }

    public static AlbumFragment getInstance() {
        return AlbumFragment.SingletonHelper.INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        setUp();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void setUp() {
        rvAlbums.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAlbums.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        if (adapter == null) {
            adapter = new AlbumAdapter();
        }
        rvAlbums.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAlbumEvent(AlbumEvent event) {
        LogUtils.d(TAG, event.getAlbums());
        adapter.setAlbums(event.getAlbums());
        adapter.notifyDataSetChanged();
    };

}
