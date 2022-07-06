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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metachef.Adapters.CartAdapter;
import com.example.metachef.Adapters.ItemsAdapter;
import com.example.metachef.Adapters.PopularAdapter;
import com.example.metachef.Interface.RandomRecipeListener;
import com.example.metachef.Interface.RecipeDetailsListener;
import com.example.metachef.R;
import com.example.metachef.RandomRecipesResponse;
import com.example.metachef.RecipeDetailsResponse;
import com.example.metachef.RequestManager;
import com.example.metachef.model.Items;

import java.util.List;

//This class represents the Carts page

public class CartFragment extends Fragment {
    private int id;
    private CartAdapter cartAdapter;
    private double tax;
    private List<Items> allItems;
    private RecyclerView rvCart;
    TextView tvItemsTotalFee, tvDeliveryFee, tvTaxFee, tvTotalFee,tvEmpty;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RequestManager manager = new RequestManager(getContext());
        manager.getRecipeDetails(responseListener, id);

        RecyclerView rvCart = view.findViewById(R.id.rvCart);
        tvItemsTotalFee = view.findViewById(R.id.tvItemsTotalFee);
        tvTaxFee = view.findViewById(R.id.tvTaxFee);
        tvTotalFee = view.findViewById(R.id.tvTotalFee);
        tvDeliveryFee = view.findViewById(R.id.tvDeliveryFee);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        ScrollView scrollView = view.findViewById(R.id.scrollView4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvCart.setLayoutManager(linearLayoutManager);
        rvCart.setAdapter(cartAdapter);
    }

    private final RecipeDetailsListener responseListener = new RecipeDetailsListener() {
        @Override
        public void didfetch(RecipeDetailsResponse response, String message) {
            CartAdapter cartAdapter = new CartAdapter(getContext(), allItems);
            rvCart.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            rvCart.setAdapter(cartAdapter);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}