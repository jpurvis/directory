package com.jpurvis.directory.network.rooms;

import com.jpurvis.directory.network.ServiceConnection;

import org.json.JSONArray;

public abstract class RoomsServiceTask extends ServiceConnection {

    private String roomsEndpoint = "rooms";

    public String roomsRequest() {
        return connect(roomsEndpoint);
    }

    public interface RoomsTaskListener {
        void onRoomsRetrieved(JSONArray rooms);
    }
}
