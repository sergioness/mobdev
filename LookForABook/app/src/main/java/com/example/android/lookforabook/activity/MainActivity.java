package com.example.android.lookforabook.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.lookforabook.Book;
import com.example.android.lookforabook.R;
import com.example.android.lookforabook.adapter.BookAdapter;
import com.example.android.lookforabook.loader.BookLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final int BOOK_LOADER_ID = 0;
    private static final int NUM_OF_RESULTS = 5;
    private static String REQUEST_URL_BOOK_TITLE = "beer";
    private static final String REQUEST_URL_PART_1 = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String REQUEST_URL_PART_2 = "&maxResults=";
    private BookAdapter adapter;
    private TextView emptyView;
    private ConnectivityManager connectivityManager;
    private EditText searchEditText;
    private ImageButton searchButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.list_view);
        emptyView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyView);
        adapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(adapter);
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = adapter.getItem(i);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getInfoLink()));

                startActivity(intent);
            }
        });

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Using this view in order to get key words to search and build query
        searchEditText = findViewById(R.id.search_edit_text);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        // Setting searchButton onClick event and proper handling
        searchButton = findViewById(R.id.search_image_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve desired book title or about
                REQUEST_URL_BOOK_TITLE = searchEditText.getText().toString();
                // Check whether device is connected to the Internet
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // If so, next it's gonna check whether any loader previously was set
                    if(getSupportLoaderManager().getLoader(BOOK_LOADER_ID) == null)
                        // In case of absence of loaders let it create one
                        getSupportLoaderManager().initLoader(BOOK_LOADER_ID, null, MainActivity.this);
                    else
                        //Otherwise restart
                        getSupportLoaderManager().restartLoader(BOOK_LOADER_ID,null, MainActivity.this);
                }
                else {
                    // If no network connection it informs user via emptyView
                    emptyView.setText(R.string.if_no_connection);
                    findViewById(R.id.progress_bar).setVisibility(View.GONE);
                }
            }
        });
    }

    private String requestUrlBuilder(String bookTitle, int numOfResults){
        return REQUEST_URL_PART_1+bookTitle+REQUEST_URL_PART_2+numOfResults;
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int i, @Nullable Bundle bundle) {
        adapter.clear();
        progressBar.setVisibility(View.VISIBLE);
        return new BookLoader(this, requestUrlBuilder(REQUEST_URL_BOOK_TITLE, NUM_OF_RESULTS));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
        progressBar.setVisibility(View.GONE);
        if (books == null || books.isEmpty()) {
            emptyView.setText(R.string.if_empty);
            return;
        }
        adapter.clear();
        adapter.addAll(books);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        adapter.clear();
    }
}
