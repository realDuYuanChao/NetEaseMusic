package shellhub.github.neteasemusic.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.SearchSingleAdapter;
import shellhub.github.neteasemusic.response.search.SearchResponse;

public class SingleSearchFragment extends Fragment {

//    private static String TAG = SingleSearchFragment.class.getSimpleName();

    @BindView(R.id.rv_single)
    RecyclerView rvSingle;

    public static SearchSingleAdapter adapter;

    private static class SingletonHelper {
        private static final SingleSearchFragment INSTANCE = new SingleSearchFragment();
    }

    public static SingleSearchFragment getInstance() {
        return SingleSearchFragment.SingletonHelper.INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_single, container, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    private void setUp() {
        rvSingle.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSingle.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        if (adapter == null) {
            adapter = new SearchSingleAdapter();
        }
        rvSingle.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchSingleEvent(SearchResponse searchResponse) {
//        LogUtils.d(TAG, searchResponse.getResult().getSongCount());
        adapter.setSongsItems(searchResponse.getResult().getSongs());
        adapter.notifyDataSetChanged();
    };
}
