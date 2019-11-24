package com.jpurvis.directory.network.rooms;

import com.jpurvis.directory.network.ServiceConnection;

public abstract class RoomsServiceTask extends ServiceConnection {

    private String roomsEndpoint = "rooms";

    String roomsRequest() {
        return connect(roomsEndpoint);
    }

    public interface RoomsTaskListener {
        void onRoomsRetrieved(String rooms);
    }
}
