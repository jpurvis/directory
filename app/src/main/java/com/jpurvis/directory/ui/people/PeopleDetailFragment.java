package com.jpurvis.directory.ui.people;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.jpurvis.directory.R;
import com.jpurvis.directory.model.Person;
import com.squareup.picasso.Picasso;

public class PeopleDetailFragment extends Fragment {

    private Person person;

    PeopleDetailFragment(Person person) {
        this.person = person;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.people_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDetail();
    }

    private void loadDetail() {
        if (getView() != null) {
            View view = getView();

            ImageView avatar = view.findViewById(R.id.avatar);
            TextView name = view.findViewById(R.id.name);
            TextView email = view.findViewById(R.id.email);
            TextView jobTitle = view.findViewById(R.id.title);
            TextView favoriteColor = view.findViewById(R.id.favorite_color);
            TextView phone = view.findViewById(R.id.phone);

            if (person.getAvatarURL() != null) {
                Picasso.get().load(person.getAvatarURL()).into(avatar);
            }

            if (person.getFirstName() != null && person.getLastName() != null) {
                String fullName = person.getFirstName() + " " + person.getLastName();
                name.setText(fullName);
                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (actionBar != null) actionBar.setSubtitle(fullName);
            } else {
                name.setVisibility(View.GONE);
            }

            if (person.getPhone() != null) {
                phone.setText(person.getPhone());
            } else {
                phone.setVisibility(View.GONE);
            }

            if (person.getJobTitle() != null) {
                jobTitle.setText(person.getJobTitle());
            } else {
                jobTitle.setVisibility(View.GONE);
            }

            if (person.getEmail() != null) {
                email.setText(person.getEmail());
            } else {
                email.setVisibility(View.GONE);
            }

            if (person.getFavouriteColor() != null) {
                favoriteColor.setText(person.getFavouriteColor());
            } else {
                favoriteColor.setVisibility(View.GONE);
            }
        }
    }


}
