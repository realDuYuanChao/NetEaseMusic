package shellhub.github.neteasemusic.adapter;

import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.ui.fragments.ArtistSearchFragment;
import shellhub.github.neteasemusic.ui.fragments.SingleSearchFragment;
import shellhub.github.neteasemusic.ui.fragments.VideoSearchFragment;
import shellhub.github.neteasemusic.util.TagUtils;

public class SearchCategoryAdapter extends FragmentPagerAdapter {
    private String TAG = TagUtils.getTag(this.getClass());
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> categoryTitles = new ArrayList<>();

    public SearchCategoryAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(SingleSearchFragment.getInstance());
        categoryTitles.add(Utils.getApp().getResources().getString(R.string.single));

        fragments.add(VideoSearchFragment.getInstance());
        categoryTitles.add(Utils.getApp().getResources().getString(R.string.video));

        fragments.add(ArtistSearchFragment.getInstance());
        categoryTitles.add(Utils.getApp().getResources().getString(R.string.artist));

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
        return categoryTitles.get(position);
    }
}
