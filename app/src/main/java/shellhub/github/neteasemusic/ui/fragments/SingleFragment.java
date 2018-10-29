package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.SingleAdapter;
import shellhub.github.neteasemusic.model.entities.SingleEvent;

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
            adapter = new SingleAdapter();
        }
        rvSingle.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingleEvent(SingleEvent event) {
        LogUtils.d(TAG, event.getSingles());
        adapter.setSingles(event.getSingles());
        adapter.notifyDataSetChanged();
    };
}
