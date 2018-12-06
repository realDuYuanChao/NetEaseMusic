package shellhub.github.neteasemusic.networking;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import shellhub.github.neteasemusic.BuildConfig;

@Module
public class NetworkModule {
    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .addHeader("Cookie", "__remember_me=true; " +
                                        "MUSIC_U=8f68d095e67f61eb13cc459fb616d94b47e5068cd50411b88eda4b883181a673b770156b3ad1280a9e8ae5c70ca8806963531a931aa80ad0; " +
                                        "__csrf=47281a4e13088a05288b11ca161c982c")
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .cache(cache)

                .build();


        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
//    @SuppressWarnings("unused")
    public NetEaseMusicAPI providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(NetEaseMusicAPI.class);
    }
    @Provides
    @Singleton
//    @SuppressWarnings("unused")
    public NetEaseMusicService providesService(
            NetEaseMusicAPI networkService) {
        return new NetEaseMusicService(networkService);
    }
}
