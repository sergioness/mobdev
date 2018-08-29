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

public class HaveABiteFragment extends Fragment implements Titlelable {

    public HaveABiteFragment(){

    }

    @Override
    public int getTitleId() {
        return R.string.have_a_bite_category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        final ArrayList<Item> items = new ArrayList<Item>(){{
            add(new Item(getString(R.string.hb_hookah_graff_header),getString(R.string.hb_hookah_graff_body), R.drawable.hb_hookah_graff_bar));
            add(new Item(getString(R.string.hb_montana_pizza_header),getString(R.string.hb_montana_pizza_body), R.drawable.hb_montana_pizza));
            add(new Item(getString(R.string.hb_sadyba_header),getString(R.string.hb_sadyba_body), R.drawable.hb_sadyba));
            add(new Item(getString(R.string.hb_wood_cafe_header),getString(R.string.hb_wood_cafe_body), R.drawable.hb_wood_cafe));
        }};

        final ItemAdapter itemsAdapter = new ItemAdapter(getActivity(), items, R.color.category_have_a_bite);

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
