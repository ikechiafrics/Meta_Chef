package com.example.metachef.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.metachef.Adapters.CartAdapter;
import com.example.metachef.R;
import com.example.metachef.RequestManager;
import com.example.metachef.model.Cart;
import com.example.metachef.model.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//This class represents the Carts page

public class CartFragment extends Fragment {
    protected CartAdapter cartAdapter;
    public SwipeRefreshLayout swipeContainer;
    private double tax;
    public static final String TAG = "Cart Fragment";
    List<Cart> allCartItems;
    private List<String> cartId = new ArrayList<>();
    private RecyclerView rvCart;
    RequestManager manager;
    TextView tvItemsTotalFee, tvDeliveryFee, tvTaxFee, tvTotalFee,tvEmpty;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                cartAdapter.clear();
                queryPosts(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        RecyclerView rvCart = view.findViewById(R.id.rvCart);
        tvItemsTotalFee = view.findViewById(R.id.tvItemsTotalFee);
        allCartItems = new ArrayList<>();
        tvTaxFee = view.findViewById(R.id.tvTaxFee);
        tvTotalFee = view.findViewById(R.id.tvTotalFee);
        tvDeliveryFee = view.findViewById(R.id.tvDeliveryFee);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        ScrollView scrollView = view.findViewById(R.id.scrollView);

        cartAdapter = new CartAdapter(getContext(), allCartItems);
        rvCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvCart.setAdapter(cartAdapter);
        queryPosts(allCartItems.size());
    }

    private void queryPosts(int size) {
        User currentUser = (User) ParseUser.getCurrentUser();
        String Id = currentUser.getObjectId();
        ParseQuery<Cart> query = ParseQuery.getQuery(Cart.class);
        query.whereEqualTo(Cart.KEY_USER, currentUser);
        query.findInBackground(new FindCallback<Cart>() {
            @Override
            public void done(List<Cart> objects, ParseException e) {
                if(e != null){
                    return;
                }
                allCartItems.addAll(objects);
                cartAdapter.notifyDataSetChanged();
            }
        });
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}