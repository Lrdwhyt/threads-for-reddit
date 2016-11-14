package com.lrdwhyt.threadsforreddit;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BrowseOnlineFragment fragment = new BrowseOnlineFragment();
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_saved_all) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BrowseLocalFragment fragment = new BrowseLocalFragment();
            Bundle args = new Bundle();
            args.putString("title", "All");
            args.putInt("cur_view", id);
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_starred) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BrowseLocalFragment fragment = new BrowseLocalFragment();
            Bundle args = new Bundle();
            args.putString("title", "Starred");
            args.putInt("cur_view", id);
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_synced) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BrowseLocalFragment fragment = new BrowseLocalFragment();
            Bundle args = new Bundle();
            args.putString("title", "Synced");
            args.putInt("cur_view", id);
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_browse) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BrowseOnlineFragment fragment = new BrowseOnlineFragment();
            Bundle args = new Bundle();
            args.putString("title", "Browse");
            args.putInt("cur_view", id);
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_settings) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SettingsFragment fragment = new SettingsFragment();
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.addToBackStack("Settings");
            fragmentTransaction.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void drawHamburger(BrowseOnlineFragment fragment) {
        drawer.removeDrawerListener(toggle);
        toggle = new ActionBarDrawerToggle(
                this, drawer, fragment.getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void drawHamburger(BrowseLocalFragment fragment) {
        drawer.removeDrawerListener(toggle);
        toggle = new ActionBarDrawerToggle(
                this, drawer, fragment.getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void disableDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void enableDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void updateDrawer(int id) {
        ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(id);
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat caller, PreferenceScreen pref) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, pref.getKey());
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.addToBackStack(pref.getTitle().toString());
        fragmentTransaction.commit();
        return true;
    }
}