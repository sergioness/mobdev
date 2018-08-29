package com.example.android.inguletstour.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.inguletstour.fragment.ChillingOutsideFragment;
import com.example.android.inguletstour.fragment.HaveABiteFragment;
import com.example.android.inguletstour.fragment.LocalsVoiceFragment;
import com.example.android.inguletstour.Titlelable;

import java.util.ArrayList;

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    private ArrayList<Titlelable> pages = new ArrayList<Titlelable>() {{
        add(new ChillingOutsideFragment());
        add(new HaveABiteFragment());
        add(new LocalsVoiceFragment());
    }};

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(pages.get(position).getTitleId());
    }

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return (Fragment) pages.get(i);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
