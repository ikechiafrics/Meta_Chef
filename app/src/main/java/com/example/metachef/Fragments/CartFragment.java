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

import com.example.metachef.Adapters.CartAdapter;
import com.example.metachef.R;

public class CartFragment extends Fragment {
    private RecyclerView rvCart;
    private CartAdapter cart_adapter;
    private double tax;
    private ScrollView scrollView;
    TextView tvItemsTotalFee, tvDeliveryFee, tvTaxFee, tvTotalFee,tvEmpty;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCart = view.findViewById(R.id.rvCart);
        tvItemsTotalFee = view.findViewById(R.id.tvItemsTotalFee);
        tvTaxFee = view.findViewById(R.id.tvTaxFee);
        tvTotalFee = view.findViewById(R.id.tvTotalFee);
        tvDeliveryFee = view.findViewById(R.id.tvDeliveryFee);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        scrollView = view.findViewById(R.id.scrollView4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvCart.setLayoutManager(linearLayoutManager);
        rvCart.setAdapter(cart_adapter);
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