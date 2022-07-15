package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.model.Cart;
import com.example.metachef.model.Food;
import com.example.metachef.model.Items;
import com.parse.ParseUser;

import org.jsoup.Jsoup;
import org.parceler.Parcels;

import java.util.List;
//This class represents the details page for each item

public class ShowDetailActivity extends AppCompatActivity {
    RequestManager manager;
    private TextView numberOrderTxt;
    private ImageView BtnBack, btnLike;
    int numberOrder = 1;
    final Food food= new Food() ;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        List<String> likeBy = food.getLikedBy();
        manager = new RequestManager(ShowDetailActivity.this);

        TextView addToCartBtn = findViewById(R.id.addToCartBtn);
        TextView titleTxt = findViewById(R.id.titleTxt);
        TextView tvFee = findViewById(R.id.tvFee);
        TextView descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        ImageView plusBtn = findViewById(R.id.plusBtn);
        ImageView minusBtn = findViewById(R.id.MinusBtn);
        ImageView picFood = findViewById(R.id.picFood);
        ImageView btnLike = findViewById(R.id.btnLike);

        Items items = Parcels.unwrap(getIntent().getParcelableExtra(Items.class.getSimpleName()));

        titleTxt.setText(items.getTitle());
        descriptionTxt.setText(Jsoup.parse(items.getSummary()).text());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        Glide.with(ShowDetailActivity.this).load(items.getImage()).transform(new RoundedCorners(90)).into(picFood);
        double prices = (items.getPricePerServing() / 10);
        String pricesStr = String.format("%.2f", prices);
        tvFee.setText(pricesStr);

        btnLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    likeBy.remove(ParseUser.getCurrentUser().getObjectId());
                    food.setLikedBy(likeBy);
                food.saveInBackground();
            }
        });

        picFood.setOnTouchListener(new View.OnTouchListener() {

            private final GestureDetector gestureDetector = new GestureDetector(ShowDetailActivity.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (!likeBy.contains(ParseUser.getCurrentUser().getObjectId())) {
                        likeBy.add(ParseUser.getCurrentUser().getObjectId());
                        food.setLikedBy(likeBy);
                        food.setImage(items.getImage());
                        food.setTitle(items.getTitle());
                        ParseUser user = ParseUser.getCurrentUser();
                        food.setUser(user);
                        btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
                        food.saveInBackground();
                    }
                    else{
                        likeBy.remove(ParseUser.getCurrentUser().getObjectId());
                        food.setLikedBy(likeBy);
                        btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
                    }
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShowDetailActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                Cart cart = new Cart();
                cart.setItem(items.getId());
                cart.setKeyImage(items.getImage());
                cart.setTitle(items.getTitle());
                double prices = (items.getPricePerServing() / 10);
                Double pricesStr = Double.valueOf(String.format("%.2f", prices));
                cart.setPrice(pricesStr);
                cart.setSize(numberOrder);
                int size = cart.getSize();
                Double price = cart.getPrice();
                cart.setItemstotal((size * price));
                ParseUser user = ParseUser.getCurrentUser();
                cart.setUser(user);
                cart.saveInBackground();


            }

        });

    }
    }

