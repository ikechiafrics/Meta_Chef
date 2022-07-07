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
import com.example.metachef.model.Cart;
import com.example.metachef.R;

import java.util.List;

//This class is what is attached to the recycler view of the cart

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final Context context;
    private final List<Cart> allCartItems;

    public CartAdapter(Context context, List<Cart> cartItems) {
        this.context = context;
        this.allCartItems = cartItems;
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCartTitle = itemView.findViewById(R.id.tvCartItem);
            ivItemPic = itemView.findViewById(R.id.ivItemPic);
            tvFeeEachItem = itemView.findViewById(R.id.tvfeeEachItem);
            tvTotalEachItem = itemView.findViewById(R.id.tvTotalEachItem);
            btnPlusItem = itemView.findViewById(R.id.btnPlusItem);
            btnMinusItem = itemView.findViewById(R.id.btnMinusItem);
            tvCartNum = itemView.findViewById(R.id.tvCartNum);
        }

        public void bind(Cart cartItems) {
            tvCartTitle.setText(cartItems.getTitle());
            tvFeeEachItem.setText(String.valueOf(cartItems.getPrice()));
            tvTotalEachItem.setText(String.valueOf(Math.round((cartItems.getSize() * cartItems.getPrice()) * 100) / 100));
            tvCartNum.setText(String.valueOf(cartItems.getSize()));
//            Glide.with(context).load(cartItems.getImage()).into(ivItemPic);
        }


    }
}
