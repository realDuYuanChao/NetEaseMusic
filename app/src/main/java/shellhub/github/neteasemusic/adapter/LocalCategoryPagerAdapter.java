package shellhub.github.neteasemusic.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.ui.fragments.AlbumFragment;
import shellhub.github.neteasemusic.ui.fragments.ArtistFragment;
import shellhub.github.neteasemusic.ui.fragments.FolderFragment;
import shellhub.github.neteasemusic.ui.fragments.SingleFragment;

public class LocalCategoryPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private String TAG = LocalCategoryPagerAdapter.class.getSimpleName();
    private List<Fragment> fragments = new ArrayList<>();

    public LocalCategoryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        fragments.add(SingleFragment.getInstance());
        fragments.add(ArtistFragment.getInstance());
        fragments.add(AlbumFragment.getInstance());
        fragments.add(FolderFragment.getInstance());
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                LogUtils.d(TAG, SingleFragment.getInstance() == null);
                fragments.get(0);
                return SingleFragment.getInstance();
            case 1:
                return new ArtistFragment();
            case 2:
                return new AlbumFragment();
            default:
                return new FolderFragment();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.single);
            case 1:
                return context.getResources().getString(R.string.artist);
            case 2:
                return context.getResources().getString(R.string.album);
            default:
                return context.getResources().getString(R.string.folder);
        }

    }
}
