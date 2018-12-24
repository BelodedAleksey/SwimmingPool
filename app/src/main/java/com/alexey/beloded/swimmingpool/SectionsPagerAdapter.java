package com.alexey.beloded.swimmingpool;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final FragmentManager fragmentManager;
    private Fragment mFragmentAtPos0;
    private int day = 1;

    private Map<Integer, String> mFragmentTags;
    private Context mContext;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null) {
            return null;
        }
        return fragmentManager.findFragmentByTag(tag);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Log.d("TAG", "getItem");
        switch (position) {
            case 0:
                //fragment = PlaceholderFragment.newInstance(day);
                fragment = new RootFragment();
                break;
            case 1:
                fragment = new Frag2();
                break;
            case 2:
                fragment = new Frag3();
                break;
            default:
                break;
        }
        return fragment;
        //return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
