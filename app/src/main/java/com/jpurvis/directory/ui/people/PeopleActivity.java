package com.jpurvis.directory.ui.people;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.jpurvis.directory.ui.main.MainActivity;
import com.jpurvis.directory.R;

public class PeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.people_container, PeopleListFragment.newInstance())
                    .commitNow();
        }

        setUpActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpActionBar();
    }

    public void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setTitle(R.string.people_title);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        fm.getBackStackEntryCount();

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            startActivityForResult(new Intent(getApplicationContext(), MainActivity.class), 0);
        } else {
            if (getSupportActionBar() != null) getSupportActionBar().setSubtitle("");
            getSupportFragmentManager().popBackStack();
        }

        return true;
    }
}
