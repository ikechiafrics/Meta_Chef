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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.Adapters.SearchAdapter;
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

    public static final String TAG = "HomeFragment";

    private RecyclerView rvItems;
    private RecyclerView rvPopular;
    private RequestManager manager;
    private ItemsAdapter itemsAdapter;
    private PopularAdapter popularAdapter;
    private ImageView ivProfilePic;
    private List<Items> allItems;
    private List<Items> allItems2;
    private List<String> tags = new ArrayList<>();



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = new RequestManager(getContext());
        manager.getRandomRecipes(responseListener, tags);
        rvItems = view.findViewById(R.id.rvItems);
        rvPopular = view.findViewById(R.id.rvPopular);
        ivProfilePic = view.findViewById(R.id.ivProfilePic);
        allItems = new ArrayList<>();
        allItems2 = new ArrayList<>();
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile image = user.getParseFile("profile_picture");
        Glide.with(getContext()).load(image.getUrl()).transform(new RoundedCorners(200)).into(ivProfilePic);

    }

    private final RandomRecipeListener responseListener = new RandomRecipeListener() {
        @Override
        public void didfetch(RandomRecipesResponse response, String message) {
            itemsAdapter = new ItemsAdapter(getContext(), allItems);
            popularAdapter = new PopularAdapter(getContext(), allItems2);
            rvItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

            for(int i = 0; i < 10; i++){
                allItems.add(response.recipes.get(i));
            }

            for(int i = 10; i < 20; i++){
                allItems2.add(response.recipes.get(i));
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