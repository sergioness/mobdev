package com.example.android.lookforabook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.lookforabook.Book;
import com.example.android.lookforabook.R;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context, @NonNull List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);

        Book currentBook = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.image_view);
        imageView.setImageBitmap(currentBook.getImageBitmap());

        TextView authorsTextView = convertView.findViewById(R.id.authors_view);
        authorsTextView.setText(currentBook.getAuthors());

        TextView titleTextView = convertView.findViewById(R.id.title_view);
        titleTextView.setText(currentBook.getTitle());

        TextView detailsTextView = convertView.findViewById(R.id.details_view);
        if (currentBook.getPublishedDate().isEmpty())
            detailsTextView.setText(currentBook.getPageCount() + " pages");
        else
            detailsTextView.setText(currentBook.getPublishedDate() + ", " + currentBook.getPageCount() + " pages");

        TextView descrpitionTextView = convertView.findViewById(R.id.description_view);
        descrpitionTextView.setText(currentBook.getDescription());

        return convertView;
    }
}
