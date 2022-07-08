package com.example.metachef.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.model.Cart;
import com.example.metachef.R;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.List;

//This class is what is attached to the recycler view of the cart

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Context context;
    private final List<Cart> allCartItems;

    public CartAdapter(Context context, List<Cart> allCartItems) {
        this.context = context;
        this.allCartItems = allCartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.cart_view, parent, false);
        return new ViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cartItems = allCartItems.get(position);
        holder.bind(cartItems);
    }

    @Override
    public int getItemCount() {return allCartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvCartTitle;
        final TextView tvFeeEachItem;
        final ImageView ivItemPic;
        final ImageView btnPlusItem;
        final ImageView btnMinusItem;
        final TextView tvTotalEachItem;
        final TextView tvCartNum;
        final ImageView btnRemove;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartTitle = itemView.findViewById(R.id.tvCartItem);
            ivItemPic = itemView.findViewById(R.id.ivItemPic);
            tvFeeEachItem = itemView.findViewById(R.id.tvfeeEachItem);
            tvTotalEachItem = itemView.findViewById(R.id.tvTotalEachItem);
            btnPlusItem = itemView.findViewById(R.id.btnPlusItem);
            btnMinusItem = itemView.findViewById(R.id.btnMinusItem);
            tvCartNum = itemView.findViewById(R.id.tvCartNum);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }

        public void bind(Cart cartItems) {
            tvCartTitle.setText(cartItems.getTitle());
            tvFeeEachItem.setText(String.valueOf(cartItems.getPrice()));
            tvTotalEachItem.setText(String.valueOf(Math.round((cartItems.getSize() * cartItems.getPrice()) * 100) / 100));
            tvCartNum.setText(String.valueOf(cartItems.getSize()));
            int roundingRadius = 60;
            Glide.with(context).load(cartItems.getKeyImage()).transform(new RoundedCorners(roundingRadius)).into(ivItemPic);

            btnPlusItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartItems.plusFoodNumber();
                    cartItems.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(context,"Increased number to " + cartItems.getSize(), Toast.LENGTH_SHORT);
                        }
                    });
                    refreshCart(cartItems);
                }
            });

            btnMinusItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartItems.minusFoodNumber();
                    cartItems.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(context,"Decreased number to " + cartItems.getSize(), Toast.LENGTH_SHORT);
                        }
                    });
                    refreshCart(cartItems);
                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartItems.deleteInBackground();
                    refreshCart(cartItems);
                }
            });
        }

        private void refreshCart(Cart cartItems) {
            tvTotalEachItem.setText(String.valueOf(Math.round((cartItems.getSize() * cartItems.getPrice()) * 100) / 100));
            tvCartNum.setText(String.valueOf(cartItems.getSize()));
        }
    }


    public void clear() {
        allCartItems.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Cart> listCart) {
        allCartItems.addAll(listCart);
        notifyDataSetChanged();
    }
}
