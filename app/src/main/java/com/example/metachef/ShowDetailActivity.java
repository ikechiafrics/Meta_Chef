package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.model.Cart;
import com.example.metachef.model.Items;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.io.File;
//This class represents the details page for each item

public class ShowDetailActivity extends AppCompatActivity {
    RequestManager manager;
    private TextView numberOrderTxt;
    private ImageView BtnBack;
    int numberOrder = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        manager = new RequestManager(ShowDetailActivity.this);

        TextView addToCartBtn = findViewById(R.id.addToCartBtn);
        TextView titleTxt = findViewById(R.id.titleTxt);
        TextView tvFee = findViewById(R.id.tvFee);
        TextView descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        ImageView plusBtn = findViewById(R.id.plusBtn);
        ImageView minusBtn = findViewById(R.id.MinusBtn);
        ImageView picFood = findViewById(R.id.picFood);

        Items items = Parcels.unwrap(getIntent().getParcelableExtra(Items.class.getSimpleName()));

        titleTxt.setText(items.getTitle());
        descriptionTxt.setText(items.getSummary());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        Glide.with(ShowDetailActivity.this).load(items.getImage()).transform(new RoundedCorners(90)).into(picFood);
        String prices = String.valueOf(items.getPricePerServing());
        tvFee.setText(prices);

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
                cart.setTitle(items.getTitle());
                File file = new File(items.getImage());
                final ParseFile imageFile = new ParseFile(items.getImage(), null);
                cart.setImage(new ParseFile(file));
                cart.setPrice(items.getPricePerServing());
                ParseUser user = ParseUser.getCurrentUser();
                cart.setUser(user);
                cart.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e!= null)
                        {
                            Log.e("error","issue with adding to database", e);
                        }
                    }
                });
            }
        });
    }
    }

