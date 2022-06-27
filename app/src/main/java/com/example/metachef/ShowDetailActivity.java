package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
    private ImageView plusBtn,MinusBtn,picFood,BtnBack;
    private Items items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        BtnBack = findViewById(R.id.BtnBack);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDetailActivity.this,HomeFragment.class);
                startActivity(i);
            }
        });
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.addToCartBtn);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        MinusBtn = findViewById(R.id.MinusBtn);
        picFood = findViewById(R.id.picFood);

        items = Parcels.unwrap(getIntent().getParcelableExtra(Items.class.getSimpleName()));

        titleTxt.setText(items.getTitle());
        descriptionTxt.setText(items.getTitle());
        Glide.with(this).load(items.getImage()).into(picFood);

    }
}