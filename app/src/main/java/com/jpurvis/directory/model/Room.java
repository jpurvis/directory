package com.jpurvis.directory.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Room {
    private String name;
    private boolean isOccupied;

    public Room(JSONObject room) {
        try {
            this.name = room.getString("name");
            this.isOccupied = room.getBoolean("isOccupied");
        } catch (JSONException e) {
            // Don't create the object
        }
    }

    public String getName() {
        return name;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
