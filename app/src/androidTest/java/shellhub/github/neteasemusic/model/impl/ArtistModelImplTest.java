package shellhub.github.neteasemusic.model.impl;

import org.junit.Test;

import java.util.List;

import shellhub.github.neteasemusic.model.ArtistModel;
import shellhub.github.neteasemusic.model.entities.Artist;

import static org.junit.Assert.*;

public class ArtistModelImplTest {

    ArtistModel artistModel = new ArtistModelImpl();
    @Test
    public void load() {
        artistModel.loadArtist(new ArtistModel.Callback() {
            @Override
            public void loadArtist(List<Artist> artists) {
                assertEquals(14, artists.size());
            }
        });
    }
}