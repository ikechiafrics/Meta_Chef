package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.Fragments.CartFragment;
import com.example.metachef.model.Cart;
import com.example.metachef.model.Items;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.jsoup.Jsoup;
import org.parceler.Parcels;

import java.io.File;
import java.util.List;
//This class represents the details page for each item

public class ShowDetailActivity extends AppCompatActivity {
    RequestManager manager;
    private TextView numberOrderTxt;
    private ImageView BtnBack, btnLike;
    int numberOrder = 1;
    Cart cart= new Cart() ;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        List<String> likeBy = cart.getLikedBy();
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
        String prices = String.valueOf(items.getPricePerServing());
        tvFee.setText(prices);

        btnLike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!likeBy.contains(ParseUser.getCurrentUser().getObjectId())) {
                    likeBy.add(ParseUser.getCurrentUser().getObjectId());
                    cart.setLikedBy(likeBy);
                    btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
                else {
                    likeBy.remove(ParseUser.getCurrentUser().getObjectId());
                    cart.setLikedBy(likeBy);
                }
                cart.saveInBackground();
            }
        });

        picFood.setOnTouchListener(new View.OnTouchListener() {

            private GestureDetector gestureDetector = new GestureDetector(ShowDetailActivity.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (!likeBy.contains(ParseUser.getCurrentUser().getObjectId())) {
                        likeBy.add(ParseUser.getCurrentUser().getObjectId());
                        cart.setLikedBy(likeBy);
                        btnLike.setImageResource(R.drawable.ic_baseline_favorite_24);
                        cart.saveInBackground();
                    }
                    else{
                        likeBy.remove(ParseUser.getCurrentUser().getObjectId());
                        cart.setLikedBy(likeBy);
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
                cart.setPrice(items.getPricePerServing());
                cart.setSize(numberOrder);
                int size = cart.getSize();
                Double price = cart.getPrice();
                double total = size * price;
                cart.setItemstotal((size * price));
                ParseUser user = ParseUser.getCurrentUser();
                cart.setUser(user);
                cart.saveInBackground();


            }

        });

    }
    }

