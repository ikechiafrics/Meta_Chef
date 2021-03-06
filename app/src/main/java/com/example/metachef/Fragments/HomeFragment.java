package com.example.metachef.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.metachef.Adapters.OthersAdapter;
import com.example.metachef.Adapters.SlideAdapter;
import com.example.metachef.model.Items;
import com.example.metachef.Adapters.ItemsAdapter;
import com.example.metachef.Adapters.PopularAdapter;
import com.example.metachef.R;
import com.example.metachef.Interface.RandomRecipeListener;
import com.example.metachef.RandomRecipesResponse;
import com.example.metachef.RequestManager;
import com.example.metachef.model.User;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//This class represents the home page

public class HomeFragment extends Fragment {
    public static final String KEY_IMAGE = "profile_picture";
    public static final String TAG = "HomeFragment";
    public User user = (User) User.getCurrentUser();

    private RecyclerView rvItems;
    private RecyclerView rvPopular;
    private RecyclerView rvOthers;
    private List<Items> allItems;
    private List<Items> popularItems;
    private List<Items> allOtherItems;
    private ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private final List<String> tags = new ArrayList<>();
    private TextView[] mDots;
    private ImageButton btnNext;
    private ImageView ivProfilePic, btnPopLike, btnOthersLike;
    private ImageButton btnPrevious;
    private int currentPage;

    public HomeFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int roundingRadius = 200;
        TextView tvWelcome = view.findViewById(R.id.tvWelcome);
        btnNext = (ImageButton) view.findViewById(R.id.btnNext);
        btnPrevious = (ImageButton) view.findViewById(R.id.btnPrevious);
        slideViewPager = (ViewPager) view.findViewById(R.id.slideViewPager);
        dotLayout = (LinearLayout) view.findViewById(R.id.dotsLayout);
        RequestManager manager = new RequestManager(getContext());
        manager.getRandomRecipes(responseListener, tags);
        rvItems = view.findViewById(R.id.rvItems);
        rvPopular = view.findViewById(R.id.rvPopular);
        rvOthers = view.findViewById(R.id.rvOthers);
        ivProfilePic = view.findViewById(R.id.ivProfilePic);

        allItems = new ArrayList<>();
        popularItems = new ArrayList<>();
        allOtherItems = new ArrayList<>();

        SlideAdapter slideAdapter = new SlideAdapter(getContext());
        slideViewPager.setAdapter(slideAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);
        ParseUser user = ParseUser.getCurrentUser();
        tvWelcome.setText("Welcome " + user.getUsername());


        user.fetchInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                displayInfo();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage + 1);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    private void displayInfo() {
        int roundingRadius = 200;
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile profilePhoto = user.getParseFile(KEY_IMAGE);
        if (profilePhoto != null) {
            Glide.with(getContext()).load(profilePhoto.getUrl()).transform(new RoundedCorners(roundingRadius)).into(ivProfilePic);
        }
        else {
            Glide.with(getContext()).load(R.drawable.profile_pic).transform(new RoundedCorners(roundingRadius)).into(ivProfilePic);
            Toast.makeText(getContext(), "profile photo does not exist for " + user.getUsername(), Toast.LENGTH_SHORT).show();
        }
    }

    private final RandomRecipeListener responseListener = new RandomRecipeListener() {
        @Override
        public void didfetch(RandomRecipesResponse response, String message) {
            ItemsAdapter itemsAdapter = new ItemsAdapter(getContext(), allItems);
            PopularAdapter popularAdapter = new PopularAdapter(getContext(), popularItems);
            OthersAdapter othersAdapter = new OthersAdapter(getContext(), allOtherItems);
            rvOthers.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            rvItems.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            rvPopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

            for(int i = 0; i < 10; i++){
                allItems.add(response.recipes.get(i));
            }

            for(int i = 10; i < 20; i++){
                popularItems.add(response.recipes.get(i));
            }
            for(int i = 20; i < 30; i++){
                allOtherItems.add(response.recipes.get(i));
            }
            rvItems.setAdapter(itemsAdapter);
            rvPopular.setAdapter(popularAdapter);
            rvOthers.setAdapter(othersAdapter);
        }

        @Override
        public void diderror(String message) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        dotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(getContext());
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);

            dotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0 ){
            mDots[position].setTextColor(getResources().getColor(R.color.purple_200));
        }
    }

    final ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

            if(position == 0){
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(false);
                btnPrevious.setVisibility(View.INVISIBLE);
            }
            else if (position == mDots.length - 1){
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(true);
                btnPrevious.setVisibility(View.VISIBLE);
            }
            else{
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(true);
                btnPrevious.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}