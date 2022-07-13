package com.example.metachef.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

//This class is what is attached to the recycler view of the Home fragment

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    private final Context context;
    private final List<Items> allItems;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tvtitle;
        private final ImageView ivitem;
        private final ImageView ivProfileImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvtitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tvtitle.setSelected(true);
            ivitem = itemView.findViewById(R.id.ivitem);
            ivProfileImg = itemView.findViewById(R.id.ivProfileImg);
            itemView.setOnClickListener(this);
        }

        public void bind(Items items) {
            ParseUser userparse = ParseUser.getCurrentUser();
            tvtitle.setText(items.getTitle());
            int roundingRadius = 40;
            Glide.with(context).load(items.getImage()).transform(new RoundedCorners(roundingRadius)).into(ivitem);
            ParseFile profilePic = userparse.getParseFile("profile_picture");

            if (ivProfileImg != null) {
                Glide.with(context).load(profilePic.getUrl()).transform(new RoundedCorners(roundingRadius)).into(ivProfileImg);
            }
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
    }
}
