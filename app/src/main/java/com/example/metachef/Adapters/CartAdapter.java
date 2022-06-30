package com.example.metachef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.metachef.model.Items;
import com.example.metachef.R;

import java.util.List;

//This class is what is attached to the recycler view of the cart

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<Items> allItems;

    public CartAdapter(Context context, List<Items> allItems) {
        this.context = context;
        this.allItems = allItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.cart_view, parent, false);
        return new ViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items items = allItems.get(position);
        holder.bind(items);
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCartTitle,tvFeeEachItem;
        ImageView ivItemPic,btnPlusItem, btnMinusItem;
        TextView tvTotalEachItem, tvCartnum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartTitle = itemView.findViewById(R.id.tvCartItem);
            ivItemPic = itemView.findViewById(R.id.ivItemPic);
            tvFeeEachItem = itemView.findViewById(R.id.tvfeeEachItem);
            tvTotalEachItem = itemView.findViewById(R.id.tvTotalEachItem);
            btnPlusItem = itemView.findViewById(R.id.btnPlusItem);
            btnMinusItem = itemView.findViewById(R.id.btnMinusItem);
            tvCartnum = itemView.findViewById(R.id.tvCartnum);
        }

        public void bind(Items items) {
            tvCartTitle.setText(items.getTitle());
            tvFeeEachItem.setText(String.valueOf(items.getNum()));
            tvTotalEachItem.setText(String.valueOf(Math.round((items.getNumberInCart() * items.getNum()) * 100) / 100));
            tvCartnum.setText(items.getNumberInCart());
            Glide.with(context).load(items.getImage()).into(ivItemPic);
        }
    }
}
