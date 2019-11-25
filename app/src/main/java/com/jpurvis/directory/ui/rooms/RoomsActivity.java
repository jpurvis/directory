package com.jpurvis.directory.ui.rooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.jpurvis.directory.R;
import com.jpurvis.directory.ui.main.MainActivity;

public class RoomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rooms_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rooms_container, RoomsListFragment.newInstance())
                    .commitNow();
        }

        setUpActionBar();
    }

    public void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setTitle(R.string.rooms_title);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        fm.getBackStackEntryCount();

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            startActivityForResult(new Intent(getApplicationContext(), MainActivity.class), 0);
        } else {
            getSupportFragmentManager().popBackStack();
        }

        return true;
    }
}
