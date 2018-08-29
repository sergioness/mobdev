package com.example.android.inguletstour.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.inguletstour.R;
import com.example.android.inguletstour.activity.ExpandedActivity;
import com.example.android.inguletstour.Item;
import com.example.android.inguletstour.Titlelable;
import com.example.android.inguletstour.adapter.ItemAdapter;

import java.util.ArrayList;

public class ChillingOutsideFragment extends Fragment implements Titlelable {

    public ChillingOutsideFragment(){

    }

    @Override
    public int getTitleId() {
        return R.string.chilling_outside_category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<Item> items = new ArrayList<Item>(){{
           add(new Item(getString(R.string.co_ingulets_park_header),getString(R.string.co_ingulets_park_body), R.drawable.co_ingulets_park));
           add(new Item(getString(R.string.co_st_mykola_church_header),getString(R.string.co_st_mykola_church_body), R.drawable.co_st_mykola_church));
           add(new Item(getString(R.string.co_glory_avenue_header),getString(R.string.co_glory_avenue_body), R.drawable.co_glory_avenue));
           add(new Item(getString(R.string.co_sc_ingulets_header),getString(R.string.co_sc_ingulets_body), R.drawable.co_sc_ingulets));
           add(new Item(getString(R.string.co_tennis_court_header),getString(R.string.co_tennis_court_body), R.drawable.co_tennis_court));
        }};

        final ItemAdapter itemsAdapter = new ItemAdapter(getActivity(), items, R.color.category_chilling_outside);

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
                intent.putExtra("ICON",item.getImageResourceId());

                startActivity(intent);
            }
        });

        return rootView;
    }
}
