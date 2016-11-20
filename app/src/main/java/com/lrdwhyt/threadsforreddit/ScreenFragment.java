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

public abstract class ScreenFragment extends Fragment {

    protected String title;
    protected Toolbar actionBar;
    protected int topLevelId;
    protected int menuResource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBar = (Toolbar) view.findViewById(R.id.actionbar);
        actionBar.inflateMenu(menuResource);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).setSupportActionBar(actionBar);
        updateTitle(title);
        ((MainActivity) getActivity()).drawHamburger(this);
        ((MainActivity) getActivity()).enableDrawer();
        ((MainActivity) getActivity()).updateDrawer(topLevelId);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(menuResource, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public Toolbar getActionBar() {
        return actionBar;
    }

    public void updateTitle(String title) {
        getActivity().setTitle(title);
    }

}
