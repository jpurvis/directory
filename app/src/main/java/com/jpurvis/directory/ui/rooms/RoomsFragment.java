package com.jpurvis.directory.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jpurvis.directory.R;
import com.jpurvis.directory.ui.people.PeopleListFragment;

public class RoomsFragment extends Fragment {

    private RoomsViewModel mViewModel;

    public static PeopleListFragment newInstance() {
        return new PeopleListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RoomsViewModel.class);
        // TODO: Use the ViewModel
    }
}
