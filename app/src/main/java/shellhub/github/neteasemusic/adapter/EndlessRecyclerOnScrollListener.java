package shellhub.github.neteasemusic.adapter;

import com.blankj.utilcode.util.LogUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import shellhub.github.neteasemusic.util.TagUtils;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private String TAG = TagUtils.getTag(this.getClass());
    /**
     * The total number of items in the dataset after the last load
     */
    private static int mPreviousTotal = 0;
    /**
     * True if we are still waiting for the last set of data to load.
     */
    private static boolean mLoading = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LogUtils.d(TAG, mLoading);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false;
                mPreviousTotal = totalItemCount;
                onLoadMore();
            }
        }
        int visibleThreshold = 5;
        if (!mLoading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            onLoadMore();

            mLoading = true;
        }
    }


    public abstract void onLoadMore();
}