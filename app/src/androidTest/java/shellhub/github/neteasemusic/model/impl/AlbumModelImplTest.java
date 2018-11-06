package shellhub.github.neteasemusic.model.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import shellhub.github.neteasemusic.model.AlbumModel;
import shellhub.github.neteasemusic.model.entities.Album;
import shellhub.github.neteasemusic.util.TagUtils;

public class AlbumModelImplTest {
    private String TAG = TagUtils.getTag(this.getClass());
    @Test
    public void loadAlbums() {
        AlbumModel albumModel = new AlbumModelImpl();
        albumModel.loadAlbums(albums -> Assert.assertEquals(0, albums.size()));
    }
}