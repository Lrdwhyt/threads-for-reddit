package com.lrdwhyt.threadsforreddit;

import android.os.Bundle;

public class ThreadListingFragment extends ScreenFragment {

    public static ThreadListingFragment newInstance(String title, int topLevelId, int menuResource) {
        Bundle fragmentParameters = new Bundle();
        fragmentParameters.putString("title", title);
        fragmentParameters.putInt("cur_view", topLevelId);
        fragmentParameters.putInt("menu_resource", menuResource);
        ThreadListingFragment fragment = new ThreadListingFragment();
        fragment.setArguments(fragmentParameters);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            title = savedInstanceState.getString("title");
            topLevelId = savedInstanceState.getInt("cur_view");
            menuResource = savedInstanceState.getInt("menu_resource");
        } else if (getArguments() != null) {
            title = getArguments().getString("title");
            topLevelId = getArguments().getInt("cur_view");
            menuResource = getArguments().getInt("menu_resource");
        } else {
            menuResource = R.menu.actionbar_thread_listing_saved;
        }
    }

}
