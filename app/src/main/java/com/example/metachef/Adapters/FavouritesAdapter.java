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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.R;
import com.example.metachef.model.Cart;
import com.example.metachef.model.Food;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder>{

    private final Context context;
    private final List<Food> allFavFood;

    public FavouritesAdapter(Context context, List<Food> allFavFood) {
        this.context = context;
        this.allFavFood = allFavFood;
    }

    @NonNull
    @Override
    public FavouritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.favourites_view, parent, false);
        return new FavouritesAdapter.ViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.ViewHolder holder, int position) {
        Food FavouriteItems = allFavFood.get(position);
        holder.bind(FavouriteItems);
    }

    @Override
    public int getItemCount() {return allFavFood.size();
    }

    public void clear() {
        allFavFood.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvFavTxt;
        final ImageView ivFavPic;
        final ImageView btnRemove;

        public ViewHolder(@NonNull View itemsView) {
            super(itemsView);
            tvFavTxt = itemsView.findViewById(R.id.tvFavTxt);
            ivFavPic = itemsView.findViewById(R.id.ivFavPic);
            btnRemove = itemsView.findViewById(R.id.btnFavRemove);
        }

        public void bind(Food favouriteItems) {
            int roundingRadius = 60;
            tvFavTxt.setText(favouriteItems.getTitle());
            Glide.with(context).load(favouriteItems.getImage()).placeholder(R.drawable.placeholder).transform(new RoundedCorners(roundingRadius)).into(ivFavPic);

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favouriteItems.deleteInBackground();
                    refreshCart(favouriteItems);
                }
            });
        }
        private void refreshCart(Food favouriteItems) {
            int roundingRadius = 60;
            tvFavTxt.setText(favouriteItems.getTitle());
            Glide.with(context).load(favouriteItems.getImage()).transform(new RoundedCorners(roundingRadius)).into(ivFavPic);
            notifyDataSetChanged();
        }
    }
}
