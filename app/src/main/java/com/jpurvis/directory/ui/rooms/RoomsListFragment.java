package com.jpurvis.directory.ui.rooms;

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
import com.jpurvis.directory.data.Room;

import java.util.ArrayList;

public class RoomsListFragment extends Fragment implements RoomsViewModel.UpdateRoomUIListener {

    private RoomsViewModel mViewModel;
    private ListView roomList;

    static RoomsListFragment newInstance() {
        return new RoomsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.room_list_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(RoomsViewModel.class);
        mViewModel.loadData(this);

        if (getView() != null) {
            roomList = getView().findViewById(R.id.room_list);
        }
    }

    @Override
    public void updateWithRooms(ArrayList<Room> rooms) {
        if (roomList != null) {
            RoomsAdapter roomAdapter = new RoomsAdapter(getContext(), rooms);
            roomList.setAdapter(roomAdapter);
        }
    }

    @Override
    public void failedToUpdate() {
        if (roomList != null) {
            roomList.setVisibility(View.GONE);
        }
    }
}
