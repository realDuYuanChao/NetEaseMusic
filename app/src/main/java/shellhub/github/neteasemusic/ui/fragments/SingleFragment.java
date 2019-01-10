package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.SingleAdapter;
import shellhub.github.neteasemusic.model.entities.SongEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleFragment extends Fragment {

    private static String TAG = SingleFragment.class.getSimpleName();

    @BindView(R.id.rv_single)
    RecyclerView rvSingle;

    public static SingleAdapter adapter;
    public SingleFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final SingleFragment INSTANCE = new SingleFragment();
    }

    public static SingleFragment getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtils.d(TAG, "SingleFragment");
        View view = inflater.inflate(R.layout.fragment_local_single, container, false);
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
        rvSingle.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSingle.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL));
        if (adapter == null) {
            adapter = new SingleAdapter();
        }
        rvSingle.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingleEvent(SongEvent event) {
        adapter.notifyDataSetChanged();
    };
}
