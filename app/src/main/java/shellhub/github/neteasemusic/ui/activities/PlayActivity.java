package shellhub.github.neteasemusic.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.LogUtils;

import androidx.appcompat.app.AppCompatActivity;
import shellhub.github.neteasemusic.R;
import shellhub.github.neteasemusic.util.TagUtils;
import shellhub.github.neteasemusic.view.PlayView;

public class PlayActivity extends AppCompatActivity implements PlayView {
    private String TAG = TagUtils.getTag(this.getClass());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                LogUtils.d(TAG, item.getTitle());
                return true;
            default:
                return false;
        }
    }

    @Override
    public void playMode() {

    }

    @Override
    public void previous() {

    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void next() {

    }

    @Override
    public void playlist() {

    }

    @Override
    public void favorite() {

    }

    @Override
    public void download() {

    }

    @Override
    public void comment() {

    }

    @Override
    public void setUpMVP() {

    }
}
