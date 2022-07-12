package com.example.metachef.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.FavouritesActivity;
import com.example.metachef.LoginActivity;
import com.example.metachef.R;
import com.example.metachef.model.User;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

//This class represents the profile page
public class ProfileFragment extends Fragment {
    public static final String KEY_IMAGE = "profile_picture";
    public User user = (User) User.getCurrentUser();
    private Button btnFav;
    private ImageView ivProfileImg;
    private ImageView btnLogout;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnFav = view.findViewById(R.id.btnFav);
        btnLogout = view.findViewById(R.id.BtnLogout);
        ivProfileImg = view.findViewById(R.id.ivProfileImg);
        ParseUser user = ParseUser.getCurrentUser();

        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                displayInfo();
            }
        });

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favourites();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void displayInfo() {
        int roundingRadius = 200;
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile profilePhoto = user.getParseFile(KEY_IMAGE);
        if (profilePhoto != null) {
            Glide.with(getContext()).load(profilePhoto.getUrl()).transform(new RoundedCorners(roundingRadius)).into(ivProfileImg);
        } else {
            Glide.with(getContext()).load(R.drawable.profile_pic).transform(new RoundedCorners(roundingRadius)).into(ivProfileImg);
            Toast.makeText(getContext(), "profile photo does not exist for " + user.getUsername(), Toast.LENGTH_SHORT).show();
        }
    }

    private void Favourites() {
        Intent intent = new Intent(getContext(), FavouritesActivity.class);
        startActivity(intent);
    }
}