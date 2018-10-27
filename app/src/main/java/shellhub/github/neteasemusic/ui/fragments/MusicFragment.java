package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.MusicMenuAdapter;
import shellhub.github.neteasemusic.model.entities.MusicMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

    @BindView(R.id.rv_music_menu)
    RecyclerView rvMusicMenu;
    private static MusicMenuAdapter adapter;
    private LinearLayoutManager layoutManager;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    private void setupAdapter(){
        adapter = new MusicMenuAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvMusicMenu.setLayoutManager(mLayoutManager);
        rvMusicMenu.setNestedScrollingEnabled(false);
//        rvMusicMenu.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvMusicMenu.setAdapter(adapter);
    }

    public static void updateData(List<MusicMenu> musicMenus) {
        adapter.setMusicMenus(musicMenus);
        adapter.notifyDataSetChanged();
    }
}
