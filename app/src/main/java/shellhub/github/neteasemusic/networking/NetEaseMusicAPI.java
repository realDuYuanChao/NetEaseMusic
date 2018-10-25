package shellhub.github.neteasemusic.networking;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shellhub.github.neteasemusic.model.login.LoginSuccessResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;

public interface NetEaseMusicAPI {
    @GET(ConstantUtils.CELLPHONE_API)
    Observable<LoginSuccessResponse> loginByPhone(@Query("phone") String phone, @Query("password") String password);
}
