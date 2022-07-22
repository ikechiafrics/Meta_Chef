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
import org.w3c.dom.Text;

import java.util.List;

//This class is what is attached to the recycler view of the Home fragment

public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.ViewHolder>{

    private final Context context;
    private final List<Items> allOtherItems;

    public OthersAdapter(Context context, List<Items> items) {
        this.context = context;
        this.allOtherItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.others_view, parent, false);
        return new ViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items items = allOtherItems.get(position);
        holder.bind(items);
    }

    @Override
    public int getItemCount() {
        return allOtherItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tvItemOthers;
        private final ImageView ivOthers;
        private final TextView tvOthersPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemOthers = itemView.findViewById(R.id.tvItemOthers);
            ivOthers = itemView.findViewById(R.id.ivOthers);
            tvOthersPrice = itemView.findViewById(R.id.tvOthersPrice);
            itemView.setOnClickListener(this);
        }

        public void bind(Items items) {
            tvItemOthers.setText(items.getTitle());
            double prices = (items.getPricePerServing() / 10);
            String pricesStr = String.format("%.2f", prices);
            tvOthersPrice.setText(pricesStr);
            int roundingRadius = 40;
            Glide.with(context).load(items.getImage()).placeholder(R.drawable.placeholder).transform(new RoundedCorners(roundingRadius)).into(ivOthers);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if(position != RecyclerView.NO_POSITION){
                Items items = allOtherItems.get(position);
                Intent intent = new Intent(context, ShowDetailActivity.class);
                intent.putExtra(Items.class.getSimpleName(), Parcels.wrap(items));
                context.startActivity(intent);
            }
        }
    }
}

