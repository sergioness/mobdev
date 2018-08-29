package com.example.android.lookforabook.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.lookforabook.Book;
import com.example.android.lookforabook.util.QueryUtils;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String url;

    public BookLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    public BookLoader(@NonNull Context context) {
        super(context);
//        this.url = default URL;
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        if (url == null || url.isEmpty()) {
            return null;
        }
        return QueryUtils.fetchBookData(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
