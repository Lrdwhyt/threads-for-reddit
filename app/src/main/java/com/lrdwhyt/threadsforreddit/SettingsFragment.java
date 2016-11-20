package com.lrdwhyt.threadsforreddit;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SettingsFragment extends PreferenceFragmentCompat {

    private Toolbar actionBar;
    private String title;

    public static SettingsFragment newInstance(String rootKey) {
        Bundle args = new Bundle();
        args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, rootKey);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Preference pref = findPreference("pref_language");
        if (pref != null) {
            pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    getActivity().recreate();
                    return true;
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //((MainActivity) getActivity()).disableDrawer();
        //getActivity().setTitle(title);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBar = (Toolbar) getView().findViewById(R.id.actionbar);
        actionBar.setTitle(title);
        actionBar.setNavigationIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_back_white_24dp));
        //((AppCompatActivity) getActivity()).setSupportActionBar(actionBar);
        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        title = getString(R.string.nav_settings);
        if (rootKey != null) {
            if (rootKey.equals("sync_settings")) {
                title = getString(R.string.pref_auto_sync);
            }
        }
    }

}
