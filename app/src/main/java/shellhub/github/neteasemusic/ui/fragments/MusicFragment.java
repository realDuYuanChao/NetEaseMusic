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
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.MusicMenuAdapter;
import shellhub.github.neteasemusic.adapter.OnClickListener;
import shellhub.github.neteasemusic.model.entities.MusicMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    private String TAG = MusicFragment.class.getSimpleName();
    @BindView(R.id.rv_music_menu)
    RecyclerView rvMusicMenu;
    private static MusicMenuAdapter adapter;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        setUp();
        return view;
    }

    private void setUp() {
        rvMusicMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMusicMenu.setAdapter(adapter = new MusicMenuAdapter());
    }

    public static void updateAdapter(List<MusicMenu> musicMenus) {
        adapter.setMusicMenus(musicMenus);
        adapter.notifyDataSetChanged();
    }
}
