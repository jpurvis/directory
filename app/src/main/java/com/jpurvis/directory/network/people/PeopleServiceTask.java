package com.jpurvis.directory.network.people;

import com.jpurvis.directory.network.ServiceConnection;

import org.json.JSONArray;

public abstract class PeopleServiceTask extends ServiceConnection {

    private String peopleEndpoint = "people";

    public String peopleRequest() {
        return connect(peopleEndpoint);
    }

    public interface PeopleTaskListener {
        void onPeopleRetrieved(JSONArray people);
    }

}
