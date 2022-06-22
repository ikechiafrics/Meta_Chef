package com.example.metachef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    Context context;
    List<Items> allItems;

    public ItemsAdapter(Context context, List<Items> items) {
        this.context = context;
        this.allItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
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
        TextView tvtitle;
        ImageView ivitem;
//        RelativeLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            ivitem = itemView.findViewById(R.id.ivitem);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        public void bind(Items items) {
            tvtitle.setText(items.getTitle());
            Glide.with(context).load(items.getImage()).into(ivitem);
        }
    }
}
