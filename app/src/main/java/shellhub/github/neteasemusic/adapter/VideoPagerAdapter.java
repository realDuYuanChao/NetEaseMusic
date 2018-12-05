package shellhub.github.neteasemusic.adapter;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.ui.fragments.HotFragment;

public class VideoPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public VideoPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());
        fragments.add(new HotFragment());

//        init video titles
        titles.add(Utils.getApp().getResources().getString(R.string.suggest));
        titles.add(Utils.getApp().getResources().getString(R.string.look_live));
        titles.add(Utils.getApp().getResources().getString(R.string.live));
        titles.add(Utils.getApp().getResources().getString(R.string.cover));
        titles.add(Utils.getApp().getResources().getString(R.string.bgm));
        titles.add(Utils.getApp().getResources().getString(R.string.mv));
        titles.add(Utils.getApp().getResources().getString(R.string.dance));
        titles.add(Utils.getApp().getResources().getString(R.string.acg_music));
        titles.add(Utils.getApp().getResources().getString(R.string.performance));
        titles.add(Utils.getApp().getResources().getString(R.string.video_top));

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
