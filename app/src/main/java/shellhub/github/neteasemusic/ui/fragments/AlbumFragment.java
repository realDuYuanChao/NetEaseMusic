package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shellhub.github.neteasemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {


    public AlbumFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final AlbumFragment INSTANCE = new AlbumFragment();
    }

    public static AlbumFragment getInstance() {
        return AlbumFragment.SingletonHelper.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

}
