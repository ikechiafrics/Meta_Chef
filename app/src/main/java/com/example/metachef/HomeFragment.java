package com.example.metachef;

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

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class HomeFragment extends Fragment {

    public static final String GET_MENU_ITEMS = "https://api.spoonacular.com/food/menuItems/search?query=wendy%27s%27s&number=10&apiKey=a0b47258ef634097812d0213ca6217ea";
    public static final String GET_POPULAR_ITEMS = "https://api.spoonacular.com/food/menuItems/search?query=pizza%27s%27s&number=5&apiKey=a0b47258ef634097812d0213ca6217ea";

    public static final String TAG = "HomeFragment";

    private RecyclerView rvItems;
    private RecyclerView rvPopular;
    private ItemsAdapter adapter;
    private PopularAdapter popular_adapter;
    List<Items> allItems;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rvItems = view.findViewById(R.id.rvItems);
        rvPopular = view.findViewById(R.id.rvPopular);
        allItems = new ArrayList<>();
        //Create adapter
        adapter = new ItemsAdapter(getContext(), allItems);
        popular_adapter = new PopularAdapter(getContext(), allItems);
        //set adapter on recyclerview
        rvItems.setAdapter(adapter);
        rvPopular.setAdapter(popular_adapter);
        rvItems.setLayoutManager(linearLayoutManager);
        rvPopular.setLayoutManager(LayoutManager);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(GET_MENU_ITEMS, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "OnSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray menuItems = jsonObject.getJSONArray("menuItems");
                    Log.i(TAG, "Food: " + menuItems.toString());
                    allItems.addAll(Items.fromJsonArray(menuItems));
                    Log.i(TAG, "Items: " + allItems.size());
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception",e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "OnFaiLURE");
            }
        });

        AsyncHttpClient user = new AsyncHttpClient();
        user.get(GET_POPULAR_ITEMS, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "OnSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray menuItems = jsonObject.getJSONArray("menuItems");
                    Log.i(TAG, "Food: " + menuItems.toString());
                    allItems.addAll(Items.fromJsonArray(menuItems));
                    Log.i(TAG, "Items: " + allItems.size());
                    popular_adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception",e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "OnFaiLURE");
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}