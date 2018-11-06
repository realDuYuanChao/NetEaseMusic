package shellhub.github.neteasemusic.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import shellhub.github.neteasemusic.ui.fragments.MusicFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new MusicFragment());
        fragments.add(new MusicFragment());
        fragments.add(new MusicFragment());
        //TODO
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
