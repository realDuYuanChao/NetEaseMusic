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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.SearchArtistAdapter;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.util.TagUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistSearchFragment extends Fragment {

    private String TAG = TagUtils.getTag(this.getClass());

    @BindView(R.id.rv_artist)
    RecyclerView rvArtist;

    private SearchArtistAdapter adapter;

    public ArtistSearchFragment() {
        // Required empty public constructor
    }

    private static class SingleHolder {
        private static ArtistSearchFragment INSTANCE = new ArtistSearchFragment();
    }

    public static ArtistSearchFragment getInstance() {
        return SingleHolder.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_search, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this, view);

        setUp();
        return view;
    }

    private void setUp() {
        rvArtist.setLayoutManager(new LinearLayoutManager(getContext()));
        if (adapter == null) {
            adapter = new SearchArtistAdapter();
        }
        rvArtist.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchArtistEvent(ArtistResponse artistResponse) {
        LogUtils.d(TAG, artistResponse);
        adapter.setArtistsItems(artistResponse.getResult().getArtists());
        adapter.notifyDataSetChanged();
    };
}
