package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.MainPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.tl_main)
    TabLayout tlMain;

    @BindView(R.id.vp_main)
    ViewPager vpMain;

    public MainFragment() {
        // Required empty public constructor
    }

    int[] actionBarIcon = {
            R.drawable.action_music,
            R.drawable.ic_discovery,
            R.drawable.ic_video
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init() {
        vpMain.setAdapter(new MainPagerAdapter(getFragmentManager()));
        tlMain.setupWithViewPager(vpMain);

        for (int i = 0; i < tlMain.getTabCount(); i++) {
            tlMain.getTabAt(i).setIcon(actionBarIcon[i]);
        }
    }

}
