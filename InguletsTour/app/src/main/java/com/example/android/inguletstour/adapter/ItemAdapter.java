package com.example.android.inguletstour.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inguletstour.Item;
import com.example.android.inguletstour.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private int colorResourceId;

    public ItemAdapter(Activity context, ArrayList<Item> items, int colorResourceId){
        super(context, 0, items);
        this.colorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_category_list, parent, false);
        }

        // Set given in the constructor color as a background color of our View
        listItemView.setBackgroundColor(ContextCompat.getColor(getContext(), colorResourceId));

        // Get the {@link Item} object located at this position in the list
        Item currentWord = getItem(position);

        // Find the TextView in the item_category_list.xml layout with the ID version_name
        TextView headerTextView = listItemView.findViewById(R.id.header_text_itl);
        // Get the version name from the current Item object and
        // set this text on the header TextView
        headerTextView.setText(currentWord.getHeader());

        // Find the TextView in the item_category_list.xml layout with the ID version_number
        TextView bodyTextView = listItemView.findViewById(R.id.body_text_itl);
        // Get the version number from the current Item object and
        // set this text on the bodytext TextView
        bodyTextView.setText(currentWord.getBodyText());

        // Find the ImageView in the item_category_list.xml layout with the ID icon_assoc
        ImageView imageView = listItemView.findViewById(R.id.imageView_item_category_list);

        if (currentWord.getImageResourceId() == -1) {
            // In case we have words to set for the LocalsVoiceFragment
            // we should omit ImageView initialization with -1 value and hide it
            imageView.setVisibility(View.GONE);

        }
        else {
            // Get the image resource ID from the current Item object and
            // set the image to imageView
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
