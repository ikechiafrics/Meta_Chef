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
import com.example.metachef.R;
import com.example.metachef.ShowDetailActivity;
import com.example.metachef.model.Items;
import com.example.metachef.model.Result;

import org.parceler.Parcels;

import java.util.List;

public class ComplexSearchAdapter extends RecyclerView.Adapter<ComplexSearchAdapter.ViewHolder> {

    private final Context context;
    private final List<Result> allSearchItems;

    public ComplexSearchAdapter(Context context, List<Result> allSearchItems) {
        this.context = context;
        this.allSearchItems = allSearchItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.item_complexsearch, parent, false);
        return new ComplexSearchAdapter.ViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int roundingRadius = 50;
        String title = allSearchItems.get(position).title;
        holder.searchTitle.setText(title);
        String image = allSearchItems.get(position).image;
        Glide.with(context).load(image).placeholder(R.drawable.placeholder).transform(new RoundedCorners(roundingRadius)).into(holder.searchImage);
    }

    @Override
    public int getItemCount() {
        return allSearchItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView searchTitle;
        private final TextView searchDescription;
        private final ImageView searchImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchTitle = itemView.findViewById(R.id.search_title);
            searchDescription = itemView.findViewById(R.id.search_description);
            searchImage = itemView.findViewById(R.id.search_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                Result result = allSearchItems.get(position);
                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtra(Items.class.getSimpleName(), Parcels.wrap(result));
                context.startActivity(intent);
            }
        }
    }
}
