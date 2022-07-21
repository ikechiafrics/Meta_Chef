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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private final List<String> tags = new ArrayList<>();

    private RequestManager manager;
    private List<Items> allItems;
    private RecyclerView rvSearch;
    private SearchAdapter searchAdapter;
    private ImageView btnFilter;
    private SearchView searchView;
    private List<String> Intolerances;
    private List<String> sort;
    private List<String> sortDirection;
    private EditText etNumber;
    private CheckBox checkGluten, checkPeanut, checkDairy, checkWheat, checkSeafood, checkPopularity, checkPrice, checkCholesterol, checkAscending, checkDescending;

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
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBottomSheet();
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

    private final SearchRecipesListener filterRecipesListener = new SearchRecipesListener() {
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

    public void toggleBottomSheet(){
        View view = getLayoutInflater().inflate(R.layout.bottomsheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getContext()).inflate(
                R.layout.bottomsheet,
                view.findViewById(R.id.bottomSheetContainer)
        );

        Intolerances = new ArrayList<>();
        sort = new ArrayList<>();
        sortDirection = new ArrayList<>();

        checkGluten = view.requireViewById(R.id.checkGluten);
        checkGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGluten.isChecked()){
                    Intolerances.add("Gluten");
                }else {
                    Intolerances.remove("Gluten");
                }
            }
        });
        checkDairy = view.requireViewById(R.id.checkDairy);
        checkDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDairy.isChecked()){
                    Intolerances.add("Dairy");
                }else {
                    Intolerances.remove("Dairy");
                }
            }
        });
        checkPeanut = view.requireViewById(R.id.checkPeanut);
        checkPeanut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPeanut.isChecked()){
                    Intolerances.add("Peanut");
                }else {
                    Intolerances.remove("Peanut");
                }
            }
        });
        checkWheat = view.requireViewById(R.id.checkWheat);
        checkWheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkWheat.isChecked()){
                    Intolerances.add("Wheat");
                } else{
                    Intolerances.remove("Wheat");
                }
            }
        });
        checkSeafood = view.requireViewById(R.id.checkSeafood);
        checkSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSeafood.isChecked()){
                    Intolerances.add("Seafood");
                } else{
                    Intolerances.remove("Seafood");
                }
            }
        });

        checkPopularity = view.requireViewById(R.id.checkPopularity);
        checkPopularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPopularity.isChecked()){
                    sort.add("popularity");
                } else{
                    sort.remove("popularity");
                }
            }
        });
        checkPrice = view.requireViewById(R.id.checkPrice);
        checkPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPrice.isChecked()){
                    sort.add("price");
                } else{
                    sort.remove("price");
                }
            }
        });
        checkCholesterol = view.requireViewById(R.id.checkCholesterol);
        checkCholesterol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCholesterol.isChecked()){
                    sort.add("cholesterol");
                } else{
                    sort.remove("cholesterol");
                }
            }
        });

        etNumber = view.requireViewById(R.id.etNumber);

        checkAscending = view.requireViewById(R.id.checkAscending);
        checkAscending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAscending.isChecked()){
                    sortDirection.add("asc");
                } else{
                    sortDirection.remove("asc");
                }
            }
        });
        checkDescending = view.requireViewById(R.id.checkDescending);
        checkDescending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSeafood.isChecked()){
                    sortDirection.add("desc");
                } else{
                    sortDirection.remove("desc");
                }
            }
        });

        String query = searchView.getQuery().toString();

        bottomSheetView.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Number = etNumber.getText().toString();
                manager.getSearchRecipes(filterRecipesListener,
                        query,
                        Intolerances,
                        sort,
                        sortDirection,
                        Number);
                Log.i("Onsuccess", "These are all your filters " + query + Intolerances + sort + sortDirection + Number);
                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
}