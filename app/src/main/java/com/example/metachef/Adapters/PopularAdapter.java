package com.example.metachef.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.model.Items;
import com.example.metachef.R;
import com.example.metachef.ShowDetailActivity;

import org.parceler.Parcels;

import java.util.List;

//This class is what is attached to the recycler view of the Home Fragment

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvpopular;
        ImageView ivpopular;
        ImageView btnAdd;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvpopular = itemView.findViewById(R.id.tvpopular);
            ivpopular = itemView.findViewById(R.id.ivpopular);
            btnAdd = itemView.findViewById(R.id.BtnAdd);
            itemView.setOnClickListener(this);
        }

        public void bind(Items items) {
            tvpopular.setText(items.getTitle());
            Glide.with(context).load(items.getImage()).transform(new RoundedCorners(30)).into(ivpopular);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Items items = allItems.get(position);
                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtra(Items.class.getSimpleName(), Parcels.wrap(items));
                context.startActivity(intent);
            }
        }
    }
}
