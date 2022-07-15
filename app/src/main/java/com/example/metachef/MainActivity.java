package com.example.metachef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;

import com.example.metachef.Fragments.CartFragment;
import com.example.metachef.Fragments.HomeFragment;
import com.example.metachef.Fragments.ProfileFragment;
import com.example.metachef.Fragments.SearchFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private final int[] icons = new int[] {R.drawable.home_icon, R.drawable.search, R.drawable.shopping_cart, R.drawable.user};

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new ViewPagerFragmentSetAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setIcon(icons[position])).attach();
    }

    public class ViewPagerFragmentSetAdapter extends FragmentStateAdapter{
        public ViewPagerFragmentSetAdapter(FragmentActivity fragmentActivity){
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new SearchFragment();
                case 2:
                    return new CartFragment();
                case 3:
                    return new ProfileFragment();
            }
            return new HomeFragment();
        }

        @Override
        public int getItemCount() {
            return icons.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds Items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}