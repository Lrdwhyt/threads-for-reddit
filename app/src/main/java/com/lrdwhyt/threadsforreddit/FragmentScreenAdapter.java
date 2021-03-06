package com.lrdwhyt.threadsforreddit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragmentScreenAdapter extends FragmentStatePagerAdapter{

    ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    FragmentManager fm;

    public FragmentScreenAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public FragmentScreenAdapter(FragmentManager fm, Fragment firstScreen) {
        this(fm);
        fragmentList.add(firstScreen);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public int getNumFragments() {
        return fragmentList.size();
    }

    public int pushFragment(Fragment fragment) {
        fragmentList.add(fragment);
        notifyDataSetChanged();
        return fragmentList.size();
    }

    public void popFragment() {
        fragmentList.remove(fragmentList.size() - 1);
        notifyDataSetChanged();
        fragmentList.get(fragmentList.size() - 1).onResume();
    }

    public void reset() {
        for (int i = 0; i < fragmentList.size(); i++) {
            fm.beginTransaction().remove(fragmentList.get(i)).commit();
            fragmentList.clear();
        }
    }
}
