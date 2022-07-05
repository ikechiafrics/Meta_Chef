package com.example.metachef.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.metachef.ShowDetailActivity;
import com.example.metachef.model.Items;

import org.parceler.Parcels;

import java.util.List;

//This class is what is attached to the recycler view of the Search Fragment
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context;
    List<Items> allItems;

    public SearchAdapter(Context context, List<Items> allItems) {
        this.context = context;
        this.allItems = allItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        return new SearchAdapter.ViewHolder(itemsView);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView search_title;
        private TextView search_description;
        private ImageView search_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_title = itemView.findViewById(R.id.search_title);
            search_description = itemView.findViewById(R.id.search_description);
            search_image = itemView.findViewById(R.id.search_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                Items items = allItems.get(position);
                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtra(Items.class.getSimpleName(), Parcels.wrap(items));
                context.startActivity(intent);
            }
        }

        public void bind(Items items) {
            search_title.setText(items.getTitle());
            search_description.setText(items.getDescription());
            Glide.with(context).load(items.getImage()).transform(new RoundedCorners(30)).into(search_image);
        }
    }


}

