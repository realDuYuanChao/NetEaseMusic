package shellhub.github.neteasemusic.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shellhub.github.neteasemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {


    public ArtistFragment() {
        // Required empty public constructor
    }

    private static class SingletonHelper {
        private static final ArtistFragment INSTANCE = new ArtistFragment();
    }

    public static ArtistFragment getInstance() {
        return ArtistFragment.SingletonHelper.INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist, container, false);
    }

}
