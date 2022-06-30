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
import com.example.metachef.R;
import com.example.metachef.ShowDetailActivity;
import com.example.metachef.model.Items;
import com.example.metachef.model.Search;

import org.parceler.Parcels;

import java.util.List;

//This class is what is attached to the recycler view of the Search Fragment
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private Context context;
    private List<Search> searches;

    public SearchAdapter(Context context, List<Search> searches) {
        this.context = context;
        this.searches = searches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SearchAdapter", "Adapter Working");
        View searchView = LayoutInflater.from(context).inflate(R.layout.item_search,parent,false);
        return new ViewHolder(searchView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("SearchAdapter", "onBinViewHolder" + position);

        Search search = searches.get(position);

        holder.bind(search);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView search_title;
        private TextView search_description;
        private ImageView search_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_title = itemView.findViewById(R.id.search_title);
            search_description = itemView.findViewById(R.id.search_description);
            search_image = itemView.findViewById(R.id.search_image);
        }

        public void bind(Search search) {
            search_title.setText(search.getTitle());
            search_description.setText(search.getDescription());
            Glide.with(context).load(search.getImage()).into(search_image);

            search_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ShowDetailActivity.class);
                    i.putExtra(Search.class.getSimpleName(), Parcels.wrap(search));
                    context.startActivity(i);
                }
            });
        }
    }

}
