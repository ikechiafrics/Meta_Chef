package com.example.metachef.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.metachef.R;

//this Class represents the Search fragment view
public class SearchFragment extends Fragment {


    public SearchFragment() {
    }



    public static SearchFragment newInstance(String param1, String param2) {

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}