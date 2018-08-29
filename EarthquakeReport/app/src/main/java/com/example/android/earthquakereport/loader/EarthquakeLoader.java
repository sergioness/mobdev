package com.example.android.earthquakereport.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.earthquakereport.Earthquake;
import com.example.android.earthquakereport.activity.MainActivity;
import com.example.android.earthquakereport.util.QueryUtils;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String url;

    public EarthquakeLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public List<Earthquake> loadInBackground() {
        if (url == null || url.isEmpty()) {
            return null;
        }
        return QueryUtils.fetchEarthquakeData(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
