package shellhub.github.neteasemusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import lombok.Data;
import shellhub.github.neteasemusic.deps.DaggerDeps;
import shellhub.github.neteasemusic.deps.Deps;
import shellhub.github.neteasemusic.networking.NetworkModule;


@Data
public class BaseApp extends AppCompatActivity {
    Deps deps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

}
