package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.metachef.Fragments.HomeFragment;
import com.example.metachef.Interface.RecipeDetailsListener;

import org.parceler.Parcels;

public class ShowDetailActivity extends AppCompatActivity {
    RequestManager manager;
    private TextView addToCartBtn;
    private TextView titleTxt, descriptionTxt, numberOrderTxt, feeTxt;
    private ImageView plusBtn, MinusBtn, picFood, BtnBack;
    private Items items;
    int numberOrder = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        manager = new RequestManager(ShowDetailActivity.this);
        //manager.getRecipeDetails();

        BtnBack = findViewById(R.id.BtnBack);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDetailActivity.this, HomeFragment.class);
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
        numberOrderTxt.setText(numberOrder);
        feeTxt.setText((int) items.getPrice());
        Glide.with(ShowDetailActivity.this).load(items.getImage()).into(picFood);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
    }


}

