package com.lrdwhyt.threadsforreddit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;

    private final String CLIENT_ID = "dEY9zJkM1CEHsQ";

    private final int MENU_BROWSE_ONLINE = R.menu.actionbar_thread_listing_online;
    private final int MENU_BROWSE_SAVED = R.menu.actionbar_thread_listing_saved;

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

        ThreadListingFragment fragment = ThreadListingFragment.newInstance(getString(R.string.nav_browse), R.id.nav_browse, MENU_BROWSE_ONLINE);
        swapTopFragment(fragment);
    }

    public void openTab(View view) {
        ImageButton v = (ImageButton) findViewById(R.id.addUser);
        String url = "https://www.reddit.com/login";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        builder.setCloseButtonIcon(bitmap);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
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
            ThreadListingFragment fragment = ThreadListingFragment.newInstance(getString(R.string.nav_all), id, MENU_BROWSE_SAVED);
            swapTopFragment(fragment);
        } else if (id == R.id.nav_starred) {
            ThreadListingFragment fragment = ThreadListingFragment.newInstance(getString(R.string.nav_starred), id, MENU_BROWSE_SAVED);
            swapTopFragment(fragment);
        } else if (id == R.id.nav_synced) {
            ThreadListingFragment fragment = ThreadListingFragment.newInstance(getString(R.string.nav_synced), id, MENU_BROWSE_SAVED);
            swapTopFragment(fragment);
        } else if (id == R.id.nav_browse) {
            ThreadListingFragment fragment = ThreadListingFragment.newInstance(getString(R.string.nav_browse), id, MENU_BROWSE_ONLINE);
            swapTopFragment(fragment);
        } else if (id == R.id.nav_settings) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SettingsFragment fragment = new SettingsFragment();
            fragmentTransaction.replace(R.id.content_container, fragment);
            fragmentTransaction.addToBackStack("Settings");
            fragmentTransaction.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void swapTopFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment);
        fragmentTransaction.commit();
    }

    public void drawHamburger(ScreenFragment fragment) {
        drawer.removeDrawerListener(toggle);
        toggle = new ActionBarDrawerToggle(
                this, drawer, fragment.getActionBar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        fragmentTransaction.replace(R.id.content_container, fragment);
        fragmentTransaction.addToBackStack(pref.getTitle().toString());
        fragmentTransaction.commit();
        return true;
    }
}