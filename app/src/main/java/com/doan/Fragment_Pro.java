package com.doan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Pro extends Fragment {
    TextView a,b,c;
    Fragment f;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__pro,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        a=(TextView) view.findViewById(R.id.txtHome);
        b=(TextView) view.findViewById(R.id.txtFav);
        c=(TextView) view.findViewById(R.id.txtlogout);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f= new FavFragment();

                loadFragment(f);
            }
        });
       a.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               f= new HomeFragment();
               loadFragment(f);
           }
       });
    }

    private void loadFragment(Fragment k)
    {
        FragmentTransaction tra= getFragmentManager().beginTransaction();
        tra.replace(R.id.frame_container, k);
        tra.addToBackStack(null);
        tra.commit();
    }
}