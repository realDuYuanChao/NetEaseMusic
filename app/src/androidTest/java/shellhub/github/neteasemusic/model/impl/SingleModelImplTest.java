package shellhub.github.neteasemusic.model.impl;

import org.junit.Test;

import java.util.List;

import shellhub.github.neteasemusic.model.SingleModel;
import shellhub.github.neteasemusic.model.entities.Single;

import static org.junit.Assert.*;

public class SingleModelImplTest {

    private SingleModelImpl singleModel;
    @Test
    public void load() {
        singleModel = new SingleModelImpl();
        singleModel.loadSingle(new SingleModel.Callback() {
            @Override
            public void loadSingle(List<Single> singles) {
                System.out.println(singles.get(0).toString());
                assertNotEquals(0, singles.size());
                assertEquals(1, singles.size());
            }
        });
    }
}