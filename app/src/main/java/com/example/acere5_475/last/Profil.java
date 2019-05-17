package com.example.acere5_475.last;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Profil extends Fragment {
    ListView data;
    String nama[] = {"Hilvia Yanurisa", "Kharisma Adelia", "Eka Yulia"};
    View view;

    public Profil(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        data = (ListView) view.findViewById(R.id.listProfil);
        data.setAdapter(new ArrayAdapter<String>(view.getContext(),
                R.layout.activity_list_profil, R.id.listProfil, nama));
    }
}