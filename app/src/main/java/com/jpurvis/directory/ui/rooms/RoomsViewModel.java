package com.jpurvis.directory.ui.rooms;

import androidx.lifecycle.ViewModel;

import com.jpurvis.directory.data.Room;
import com.jpurvis.directory.network.rooms.RoomsServiceTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class RoomsViewModel extends ViewModel implements RoomsServiceTask.RoomsTaskListener {
    private ArrayList<Room> rooms;
    private UpdateRoomUIListener updateRoomUIListener;

    void loadData(UpdateRoomUIListener updateRoomUIListener) {
        this.updateRoomUIListener = updateRoomUIListener;
        LoadRoomDataTask loadRoomDataTask = new LoadRoomDataTask(this);
        loadRoomDataTask.execute();
    }

    public void onRoomsRetrieved(JSONArray roomsArray) {
        // Update UI
        rooms = new ArrayList<>();
        for (int i = 0; i < roomsArray.length(); i++) {
            try {
                rooms.add(new Room(roomsArray.getJSONObject(i)));
            } catch (JSONException e) {
                // Do nothing
            }
        }

        if (rooms.size() > 0) {
            updateRoomUIListener.updateWithRooms(rooms);
        } else {
            updateRoomUIListener.failedToUpdate();
        }
    }

    public interface UpdateRoomUIListener {
        void updateWithRooms(ArrayList<Room> rooms);

        void failedToUpdate();
    }

    private static class LoadRoomDataTask extends RoomsServiceTask {
        private RoomsTaskListener roomTaskListener;

        LoadRoomDataTask(RoomsTaskListener roomTaskListener) {
            this.roomTaskListener = roomTaskListener;
        }

        @Override
        protected String doInBackground(String... params) {
            return roomsRequest();
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                JSONArray rooms = new JSONArray(response);
                if (roomTaskListener != null) {
                    roomTaskListener.onRoomsRetrieved(rooms);
                }
            } catch (JSONException e) {
                roomTaskListener.onRoomsRetrieved(null);
            }
        }
    }
}
