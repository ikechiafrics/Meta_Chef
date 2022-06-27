package com.example.metachef;

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
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcels;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvtitle;
        private ImageView ivitem;
        private ImageView ivProfileImg;
//        RelativeLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            ivitem = itemView.findViewById(R.id.ivitem);
            ivProfileImg = itemView.findViewById(R.id.ivProfileImg);
            itemView.setOnClickListener(this);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        public void bind(Items items) {
            ParseUser userparse = ParseUser.getCurrentUser();
            tvtitle.setText(items.getTitle());
            Glide.with(context).load(items.getImage()).into(ivitem);
            ParseFile profilePic = userparse.getParseFile("profile_picture");
            Log.i("Adpater",profilePic.getUrl());
            if (ivProfileImg != null) {
                Glide.with(context).load(profilePic.getUrl()).transform(new RoundedCorners(90)).into(ivProfileImg);
            }
//            User user = new User();
//            ParseFile image = user.getPicture();
//            if (image != null) {
//                Glide.with(context).load(image.getUrl()).transform(new RoundedCorners(90)).into(ivProfilePic);
//            }
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
