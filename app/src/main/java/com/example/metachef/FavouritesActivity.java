package com.example.metachef;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.metachef.Adapters.FavouritesAdapter;
import com.example.metachef.model.Food;
import com.example.metachef.model.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {
    public SwipeRefreshLayout swipeContainer;
    public FavouritesAdapter favouritesAdapter;
    private TextView tvFavTxt;
    private ImageView ivFavPic;
    private List<Food> allFavFood;
    private RecyclerView rvFavourites;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.favswipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favouritesAdapter.clear();
                queryFavourites(0);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        int roundingRadius = 200;
        rvFavourites = findViewById(R.id.rvFavourites);
        allFavFood = new ArrayList<>();
        ivFavPic = findViewById(R.id.ivFavPic);
        tvFavTxt = findViewById(R.id.tvFavTxt);

        favouritesAdapter = new FavouritesAdapter(FavouritesActivity.this, allFavFood);
        rvFavourites.setAdapter(favouritesAdapter);
        rvFavourites.setLayoutManager(new LinearLayoutManager(FavouritesActivity.this, LinearLayoutManager.VERTICAL, false));
        queryFavourites(allFavFood.size());
    }

    private void queryFavourites(int size) {
        User currentUser = (User) ParseUser.getCurrentUser();
        String Id = currentUser.getObjectId();
        ParseQuery<Food> query = ParseQuery.getQuery(Food.class);
        query.whereEqualTo(Food.KEY_USER, currentUser);
        query.findInBackground(new FindCallback<Food>() {
            @Override
            public void done(List<Food> objects, ParseException e) {
                if(e != null){
                    return;
                }
                allFavFood.addAll(objects);
                favouritesAdapter.notifyDataSetChanged();
            }
        });
        swipeContainer.setRefreshing(false);
    }
}
