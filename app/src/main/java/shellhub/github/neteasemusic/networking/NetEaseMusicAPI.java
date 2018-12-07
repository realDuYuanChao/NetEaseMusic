package shellhub.github.neteasemusic.networking;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shellhub.github.neteasemusic.response.banner.BannerResponse;
import shellhub.github.neteasemusic.response.comment.CommentResponse;
import shellhub.github.neteasemusic.response.detail.DetailResponse;
import shellhub.github.neteasemusic.response.login.LoginResponse;
import shellhub.github.neteasemusic.response.playlist.PlaylistDetailResponse;
import shellhub.github.neteasemusic.response.recommend.resource.RecommendSongListResponse;
import shellhub.github.neteasemusic.response.search.SearchResponse;
import shellhub.github.neteasemusic.response.search.artist.ArtistResponse;
import shellhub.github.neteasemusic.response.search.hot.HotResponse;
import shellhub.github.neteasemusic.response.search.mp3.SongResponse;
import shellhub.github.neteasemusic.response.search.song.detail.SongDetailResponse;
import shellhub.github.neteasemusic.response.search.video.VideoResponse;
import shellhub.github.neteasemusic.util.ConstantUtils;

public interface NetEaseMusicAPI {
    @GET(ConstantUtils.CELLPHONE_API)
    Observable<LoginResponse> loginByPhone(@Query("phone") String phone, @Query("password") String password);

    @GET(ConstantUtils.USER_DETAIL_API)
    Observable<DetailResponse> detail(@Query("uid") String uid);

    @GET(ConstantUtils.SEARCH_API)
    Observable<SearchResponse> search(@Query("keywords") String keywords);

    @GET(ConstantUtils.SEARCH_API)
    Observable<SearchResponse> search(@Query("keywords") String keywords, @Query("offset") int offset); //page

    @GET(ConstantUtils.COMMENT_API)
    Observable<CommentResponse> comment(@Query("id") int id);

    @GET(ConstantUtils.COMMENT_API)
    Observable<CommentResponse> comment(@Query("id") int id, @Query(value = "limit") int limit);

    @GET(ConstantUtils.SEARCH_HOT_API)
    Observable<HotResponse> searchHot();

    @GET(ConstantUtils.SEARCH_API + "?type=1014")
    Observable<VideoResponse> searchVideo(@Query("keywords") String keywords);

    @GET(ConstantUtils.SEARCH_API + "?type=100")
    Observable<ArtistResponse> searchArtist(@Query("keywords") String keyword);

    @GET(ConstantUtils.SONG_URL_API)
    Observable<SongResponse> getSongUrl(@Query("id") int id);

    @GET(ConstantUtils.SONG_DETAIL_API)
    Observable<SongDetailResponse> getSongDetail(@Query("ids") int ids);

    @GET(ConstantUtils.BANNER_API)
    Observable<BannerResponse> getBanner();

    @GET(ConstantUtils.RECOMMEND_SONG_LIST_API)
    Observable<RecommendSongListResponse> getRecommendSongList();

    @GET(ConstantUtils.PLAYLIST_DETAIL_API)
    Observable<PlaylistDetailResponse> getPlaylistDetail(@Query("id") long id);
}
