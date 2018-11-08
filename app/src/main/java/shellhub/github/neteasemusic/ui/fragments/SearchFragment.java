package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.adapter.LocalCategoryPagerAdapter;
import shellhub.github.neteasemusic.adapter.SearchCategoryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    @BindView(R.id.vp_search_category)
    ViewPager vpSearchCategory;

    @BindView(R.id.tl_search_category)
    TabLayout tbSearchCategory;

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        ButterKnife.bind(this, view);

        setup();
        return view;
    }

    private void setup(){
        vpSearchCategory.setOffscreenPageLimit(3);
        vpSearchCategory.setAdapter(new SearchCategoryAdapter(getFragmentManager()));
        tbSearchCategory.setupWithViewPager(vpSearchCategory);
    }


}
