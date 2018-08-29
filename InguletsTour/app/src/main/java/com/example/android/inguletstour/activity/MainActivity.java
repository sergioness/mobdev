package com.example.android.inguletstour.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.inguletstour.R;
import com.example.android.inguletstour.adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager_mainactivity);
        viewPager.setAdapter(new CategoryAdapter(this, getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tablayout_mainactivity);
        tabLayout.setupWithViewPager(viewPager);
    }
}
