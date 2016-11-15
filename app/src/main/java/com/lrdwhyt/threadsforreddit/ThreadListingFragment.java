package com.lrdwhyt.threadsforreddit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThreadListingFragment extends ScreenFragment {

    public static ThreadListingFragment newInstance(String title, int topLevelId, int menuResource) {
        ThreadListingFragment fragment = new ThreadListingFragment();

        Bundle fragmentParameters = new Bundle();
        fragmentParameters.putString("title", title);
        fragmentParameters.putInt("cur_view", topLevelId);
        fragmentParameters.putInt("menu_resource", menuResource);
        fragment.setArguments(fragmentParameters);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thread_listing, container, false);
    }

}
