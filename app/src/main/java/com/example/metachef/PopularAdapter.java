package com.example.metachef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>{

    Context context;
    List<Items> allItems;

    public PopularAdapter(Context context, List<Items> items) {
        this.context = context;
        this.allItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.popular_view, parent, false);
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
        TextView tvpopular;
        ImageView ivpopular;
        TextView tvprice;
        ImageView btnAdd;
//        RelativeLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvpopular = itemView.findViewById(R.id.tvpopular);
            ivpopular = itemView.findViewById(R.id.ivpopular);
//            tvprice = itemView.findViewById(R.id.tvprice);
            btnAdd = itemView.findViewById(R.id.BtnAdd);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        public void bind(Items items) {
            tvpopular.setText(items.getTitle());
            Glide.with(context).load(items.getImage()).into(ivpopular);
//            tvprice.setText(items.getPrice());
        }
    }
}
