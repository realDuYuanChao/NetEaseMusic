package shellhub.github.neteasemusic.networking;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import shellhub.github.neteasemusic.response.comment.CommentResponse;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.response.login.LoginResponse;
import shellhub.github.neteasemusic.response.search.SearchResponse;

public class NetEaseMusicService {
    private NetEaseMusicAPI netEaseMusicAPI;

    public NetEaseMusicService(NetEaseMusicAPI netEaseMusicAPI) {
        this.netEaseMusicAPI = netEaseMusicAPI;
    }

    public void login(String phone, String password, final Callback callback) {
        netEaseMusicAPI.loginByPhone(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        callback.onSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void detail(String uid, final Callback callback) {
        netEaseMusicAPI.detail(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailResponse detailResponse) {
                        callback.onSuccess(detailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void search(String keywords, final Callback callback) {
        netEaseMusicAPI.search(keywords).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchResponse searchResponse) {
                        callback.onSuccess(searchResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void comment(int id, final Callback callback) {
        netEaseMusicAPI.comment(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentResponse commentResponse) {
                        callback.onSuccess(commentResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface Callback<T> {
        void onSuccess(T data);

        void onError(Throwable e);
    }
}
