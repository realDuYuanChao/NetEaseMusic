package shellhub.github.neteasemusic.presenter.impl;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import shellhub.github.neteasemusic.model.SingleModel;
import shellhub.github.neteasemusic.model.entities.Single;
import shellhub.github.neteasemusic.model.impl.SingleModelImpl;
import shellhub.github.neteasemusic.presenter.LocalPresenter;
import shellhub.github.neteasemusic.view.LocalView;

public class LocalPresenterImpl implements LocalPresenter, SingleModel.Callback {
    private String TAG = LocalPresenterImpl.class.getSimpleName();
    private LocalView mLocalView;
    private SingleModel singleModel;

    public LocalPresenterImpl(LocalView mLocalView) {
        this.mLocalView = mLocalView;
        this.singleModel = new SingleModelImpl();
    }

    @Override
    public void load() {
        singleModel.load(this);
    }

    @Override
    public void load(List<Single> singles) {
        LogUtils.d(TAG, singles.toString() + "-----");
        mLocalView.loadSingle(singles);
    }
}
