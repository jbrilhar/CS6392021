package pace.cs639.whowroteitLoader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.jetbrains.annotations.NotNull;

public class BookLoader extends AsyncTaskLoader<String> {
    private String mQueryString;
    @Override
    protected void onStartLoading() {
        forceLoad();
        super.onStartLoading();
    }

    public BookLoader(@NonNull @NotNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

}
