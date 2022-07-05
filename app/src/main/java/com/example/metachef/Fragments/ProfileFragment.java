package com.example.metachef.Fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.LoginActivity;
import com.example.metachef.R;
import com.parse.ParseFile;
import com.parse.ParseUser;

//This class represents the profile page
public class ProfileFragment extends Fragment {
    public static final String KEY_IMAGE = "profile_picture";

    public ProfileFragment() {
        // Required empty public constructor
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
        int roundingRadius = 200;
        ImageView btnLogout = view.findViewById(R.id.BtnLogout);
        ImageView ivProfileImg = view.findViewById(R.id.ivProfileImg);
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile img = user.getParseFile(KEY_IMAGE);
        Glide.with(getContext()).load(img.getUrl()).transform(new RoundedCorners(roundingRadius)).into(ivProfileImg);
        super.onViewCreated(view, savedInstanceState);
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
}