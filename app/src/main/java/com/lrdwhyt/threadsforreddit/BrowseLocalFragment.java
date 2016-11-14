package com.lrdwhyt.threadsforreddit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class BrowseLocalFragment extends Fragment {

    private Toolbar toolbar;
    private String title;
    private int curView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            title = savedInstanceState.getString("title", "Threads");
            curView = savedInstanceState.getInt("cur_view", 0);
        } else if (getArguments() != null) {
            title = getArguments().getString("title", "Threads");
            curView = getArguments().getInt("cur_view", 0);
        } else {
            title = "Threads";
            curView = 0;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(title);
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).drawHamburger(this);
        ((MainActivity) getActivity()).enableDrawer();
        ((MainActivity) getActivity()).updateDrawer(curView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browse_offline, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.actionbar_offline_overview, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

}
