package com.example.android.inguletstour.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.inguletstour.R;
import com.example.android.inguletstour.activity.ExpandedActivity;
import com.example.android.inguletstour.Item;
import com.example.android.inguletstour.Titlelable;
import com.example.android.inguletstour.adapter.ItemAdapter;

import java.util.ArrayList;

public class LocalsVoiceFragment extends Fragment implements Titlelable {

    public LocalsVoiceFragment(){

    }

    @Override
    public int getTitleId() {
        return R.string.locals_voice_category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<Item> items = new ArrayList<Item>(){{
            add(new Item(getString(R.string.lv_1_header),getString(R.string.lv_1_body), -1));
            add(new Item(getString(R.string.lv_2_header),getString(R.string.lv_2_body), -1));
        }};

        final ItemAdapter itemsAdapter = new ItemAdapter(getActivity(), items, R.color.category_locals_voice);

        ListView listView = rootView.findViewById(R.id.listview_categorylist);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Open new Activity with full description of chosen place
                Item item = items.get(i);

                Intent intent = new Intent(getActivity(), ExpandedActivity.class);
                intent.putExtra(Intent.EXTRA_TITLE,item.getHeader());
                intent.putExtra(Intent.EXTRA_TEXT,item.getBodyText());

                startActivity(intent);
            }
        });

        return rootView;
    }
}
