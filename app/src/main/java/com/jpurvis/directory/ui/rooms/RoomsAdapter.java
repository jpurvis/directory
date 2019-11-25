package com.jpurvis.directory.ui.rooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jpurvis.directory.R;
import com.jpurvis.directory.data.Room;

import java.util.ArrayList;

public class RoomsAdapter extends ArrayAdapter<Room> {
    RoomsAdapter(Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Room room = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.room_cell, parent,
                    false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView isOccupied = convertView.findViewById(R.id.occupied);

        if (room.getName() != null) {
            name.setText(room.getName());
            if (room.isOccupied()) {
                isOccupied.setText(R.string.room_is_occupied);
            } else {
                isOccupied.setText(R.string.room_is_unoccupied);
            }
        } else {
            convertView.setVisibility(View.GONE);
        }

        return convertView;
    }
}
