package shellhub.github.neteasemusic.model.impl;

import org.junit.Test;

import shellhub.github.neteasemusic.model.HotModel;

import static org.junit.Assert.*;

public class HotModelImplTest {

    @Test
    public void getBanner() {
        HotModel hotModel = new HotModelImpl();
        hotModel.getBanner((System.out::println));
    }
}