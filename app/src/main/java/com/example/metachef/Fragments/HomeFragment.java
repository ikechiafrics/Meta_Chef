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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.model.Items;
import com.example.metachef.Adapters.ItemsAdapter;
import com.example.metachef.Adapters.PopularAdapter;
import com.example.metachef.R;
import com.example.metachef.Interface.RandomRecipeListener;
import com.example.metachef.RandomRecipesResponse;
import com.example.metachef.RequestManager;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//This class represents the home page

public class HomeFragment extends Fragment {
    public static final String KEY_IMAGE = "profile_picture";
    public static final String TAG = "HomeFragment";

    private RecyclerView rvItems;
    private RecyclerView rvPopular;
    private List<Items> allItems;
    private List<Items> popularItems;
    private final List<String> tags = new ArrayList<>();



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int roundingRadius = 200;
        RequestManager manager = new RequestManager(getContext());
        manager.getRandomRecipes(responseListener, tags);
        rvItems = view.findViewById(R.id.rvItems);
        rvPopular = view.findViewById(R.id.rvPopular);
        ImageView ivProfilePic = view.findViewById(R.id.ivProfilePic);
        allItems = new ArrayList<>();
        popularItems = new ArrayList<>();
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile image = user.getParseFile(KEY_IMAGE);
        Glide.with(getContext()).load(image.getUrl()).transform(new RoundedCorners(roundingRadius)).into(ivProfilePic);

    }

    private final RandomRecipeListener responseListener = new RandomRecipeListener() {
        @Override
        public void didfetch(RandomRecipesResponse response, String message) {
            ItemsAdapter itemsAdapter = new ItemsAdapter(getContext(), allItems);
            PopularAdapter popularAdapter = new PopularAdapter(getContext(), popularItems);
            rvItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

            for(int i = 0; i < 10; i++){
                allItems.add(response.recipes.get(i));
            }

            for(int i = 10; i < 20; i++){
                popularItems.add(response.recipes.get(i));
            }
            Log.e("OnSuccess", "this is working");
            rvItems.setAdapter(itemsAdapter);
            rvPopular.setAdapter(popularAdapter);
        }

        @Override
        public void diderror(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
}