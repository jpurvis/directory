package com.jpurvis.directory.ui.people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jpurvis.directory.R;
import com.jpurvis.directory.data.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeopleAdapter extends ArrayAdapter<Person> {
    PeopleAdapter(Context context, ArrayList<Person> people) {
        super(context, 0, people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.people_cell, parent,
                    false);
        }

        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView name = convertView.findViewById(R.id.name);
        TextView jobTitle = convertView.findViewById(R.id.title);

        if (person.getAvatarURL() != null) {
            Picasso.get().load(person.getAvatarURL()).into(avatar);
        }

        if (person.getFirstName() != null && person.getLastName() != null) {
            String fullName = person.getFirstName() + " " + person.getLastName();
            name.setText(fullName);
        } else {
            name.setVisibility(View.GONE);
        }

        if (person.getJobTitle() != null) {
            jobTitle.setText(person.getJobTitle());
        } else {
            jobTitle.setVisibility(View.GONE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                PeopleDetailFragment peopleDetailFragment = new PeopleDetailFragment(person);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.people_container,
                        peopleDetailFragment).addToBackStack("PeopleDetail").commit();
            }
        });

        // Return the completed convertView to render on screen
        return convertView;
    }
}
