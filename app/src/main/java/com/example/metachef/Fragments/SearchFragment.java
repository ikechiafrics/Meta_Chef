package com.example.metachef.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.metachef.Adapters.ComplexSearchAdapter;
import com.example.metachef.Adapters.SearchAdapter;
import com.example.metachef.Interface.RandomRecipeListener;
import com.example.metachef.Interface.SearchRecipesListener;
import com.example.metachef.R;
import com.example.metachef.RandomRecipesResponse;
import com.example.metachef.RequestManager;
import com.example.metachef.SearchRecipesResponse;
import com.example.metachef.model.Items;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

//this Class represents the Search fragment view
public class SearchFragment extends Fragment {
    public static final String TAG = "Search Fragment";
    private final List<String> tags = new ArrayList<>();

    private RequestManager manager;
    private List<Items> allItems;
    private RecyclerView rvSearch;
    private SearchAdapter searchAdapter;
    private ImageView btnFilter;
    private SearchView searchView;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.etSearch2);
        manager = new RequestManager(getContext());
        manager.getRandomRecipes(responseListener, tags);
        rvSearch = view.findViewById(R.id.rvSearch);
        searchAdapter = new SearchAdapter(getContext(), allItems);
        rvSearch.setAdapter(searchAdapter);
        btnFilter = view.findViewById(R.id.btnFilter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                manager.getSearchRecipes(searchRecipesListener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getContext()).inflate(
                        R.layout.bottomsheet,
                        view.findViewById(R.id.bottomSheetContainer)
                );
                bottomSheetView.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

    private final RandomRecipeListener responseListener = new RandomRecipeListener() {
        @Override
        public void didfetch(RandomRecipesResponse response, String message) {
            searchAdapter = new SearchAdapter(getContext(), response.recipes);
            rvSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvSearch.setAdapter(searchAdapter);
        }

        @Override
        public void diderror(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SearchRecipesListener searchRecipesListener = new SearchRecipesListener() {
        @Override
        public void didfetch(SearchRecipesResponse response, String message) {
            rvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
            ComplexSearchAdapter adapter = new ComplexSearchAdapter(getContext(), response.results);
            rvSearch.setAdapter(adapter);
        }

        @Override
        public void diderror(String message) {

        }
    };
}