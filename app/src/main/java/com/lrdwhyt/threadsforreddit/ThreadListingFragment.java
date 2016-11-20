package com.lrdwhyt.threadsforreddit;

import android.os.Bundle;
import android.util.Log;

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
            Log.d("2", savedInstanceState.toString());
            title = savedInstanceState.getString("title", "failure2");
            topLevelId = savedInstanceState.getInt("cur_view");
            menuResource = savedInstanceState.getInt("menu_resource");
        } else if (getArguments() != null) {
            title = getArguments().getString("title", "failure1");
            topLevelId = getArguments().getInt("cur_view");
            menuResource = getArguments().getInt("menu_resource");
        } else {
            title = "failure";
            menuResource = R.menu.actionbar_thread_listing_saved;
        }
    }

}
