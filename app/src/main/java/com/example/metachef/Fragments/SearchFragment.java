package com.example.metachef.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.metachef.Adapters.SearchAdapter;
import com.example.metachef.R;
import com.example.metachef.model.Search;

import java.util.ArrayList;
import java.util.List;

//this Class represents the Search fragment view
public class SearchFragment extends Fragment {
    public static final String TAG = "Search Fragment";
    EditText etSearch2;
    RecyclerView rvSearch2;
    SearchAdapter searchAdapter;
    ImageView btnSearch;
    List<Search> searches;


    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSearch2 = view.findViewById(R.id.rvSearch2);
        searches = new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext(), searches);
        rvSearch2.setAdapter(searchAdapter);
        btnSearch = view.findViewById(R.id.btnSearch);
        rvSearch2.setLayoutManager(new LinearLayoutManager(getContext()));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSearch(v);
            }
        });

    }

    private void createSearch(View v) {
        String searchInput = (String) etSearch2.getText().toString();
    }
}