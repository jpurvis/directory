package com.jpurvis.directory.ui.people;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jpurvis.directory.R;
import com.jpurvis.directory.data.Person;

import java.util.ArrayList;

public class PeopleListFragment extends Fragment implements PeopleViewModel.UpdatePeopleUIListener {

    private PeopleViewModel mViewModel;
    private ListView peopleList;

    public static PeopleListFragment newInstance() {
        return new PeopleListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.people_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);
        mViewModel.loadData(this);

        if (getView() != null) {
            peopleList = getView().findViewById(R.id.people_list);
        }
    }

    @Override
    public void updateWithPeople(ArrayList<Person> people) {
        if (peopleList != null) {
            PeopleAdapter peopleAdapter = new PeopleAdapter(getContext(), people);
            peopleList.setAdapter(peopleAdapter);
        }
    }

    @Override
    public void failedToUpdate() {
        if (peopleList != null) {
            peopleList.setVisibility(View.GONE);
        }
    }
}
