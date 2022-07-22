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
import com.example.metachef.QuickAnswerResponse;
import com.example.metachef.R;
import com.example.metachef.model.QuickAnswer;

import java.util.List;

public class QuickAnswerAdapter extends RecyclerView.Adapter<QuickAnswerAdapter.ViewHolder>{
    private final Context context;
    private final String answer;
    private final String image;
    private final List<QuickAnswer> answerList;

    public QuickAnswerAdapter(Context context, String answer, String image, List<QuickAnswer> answerList) {
        this.context = context;
        this.answer = answer;
        this.image = image;
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(context).inflate(R.layout.quickanswer, parent, false);
        return new QuickAnswerAdapter.ViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int roundingRadius = 50;
        String answer = answerList.get(position).answer;
        holder.tvAnswer.setText(answer);
        String image = answerList.get(position).image;
        Glide.with(context).load(image).placeholder(R.drawable.placeholder).transform(new RoundedCorners(roundingRadius)).into(holder.ivAnswer);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivAnswer;
        private final TextView tvAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnswer = itemView.findViewById(R.id.ivAnswer);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
        }
    }
}
