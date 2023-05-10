package com.doan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FavFragment extends Fragment {

    ListView lstv;
    ArrayList<Location> arrayList = new ArrayList<>();
    LocationAdapter locationAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lstv=(ListView) view.findViewById(R.id.favlst);
        //arrayList.add(new Location("Hồ Chí Minh", Location.convertStringToBitmapFromAccess(getContext(), "hcm.png")));
        locationAdapter = new LocationAdapter(getContext(), R.layout.layout_item_location, arrayList);
        lstv.setAdapter(locationAdapter);
    }
}