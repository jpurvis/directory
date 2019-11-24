package com.jpurvis.directory.ui.people;

import androidx.lifecycle.ViewModel;

import com.jpurvis.directory.data.Person;
import com.jpurvis.directory.network.people.PeopleServiceTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class PeopleViewModel extends ViewModel implements PeopleServiceTask.PeopleTaskListener {

    private ArrayList<Person> people;
    private UpdatePeopleUIListener updatePeopleUIListener;

    void loadData(UpdatePeopleUIListener updatePeopleUIListener) {
        this.updatePeopleUIListener = updatePeopleUIListener;
        LoadPeopleDataTask loadPeopleDataTask = new LoadPeopleDataTask(this);
        loadPeopleDataTask.execute();
    }

    @Override
    public void onPeopleRetrieved(JSONArray peopleArray) {
        // Update UI
        people = new ArrayList<>();
        for (int i = 0; i < peopleArray.length(); i++) {
            try {
                people.add(new Person(peopleArray.getJSONObject(i)));
            } catch (JSONException e) {
                // Do nothing
            }
        }

        if (people.size() > 0) {
            updatePeopleUIListener.updateWithPeople(people);
        } else {
            updatePeopleUIListener.failedToUpdate();
        }
    }

    public interface UpdatePeopleUIListener {
        void updateWithPeople(ArrayList<Person> people);

        void failedToUpdate();
    }

    private static class LoadPeopleDataTask extends PeopleServiceTask {
        private PeopleTaskListener peopleTaskListener;

        LoadPeopleDataTask(PeopleTaskListener peopleTaskListener) {
            this.peopleTaskListener = peopleTaskListener;
        }

        @Override
        protected String doInBackground(String... params) {
            return peopleRequest();
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                JSONArray people = new JSONArray(response);
                if (peopleTaskListener != null) {
                    peopleTaskListener.onPeopleRetrieved(people);
                }
            } catch (JSONException e) {
                peopleTaskListener.onPeopleRetrieved(null);
            }
        }
    }
}
