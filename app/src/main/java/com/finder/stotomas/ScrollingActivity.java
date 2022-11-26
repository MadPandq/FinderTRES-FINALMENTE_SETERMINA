package com.finder.stotomas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ScrollingActivity extends AppCompatActivity {

    private static final String TAG = "ScrollingActivity";
    private ViewPager viewPager;
    private Button skip, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        skip = findViewById(R.id.skipButton);
        next = findViewById(R.id.nextbtn);
        viewPager = findViewById(R.id.viewPager);

        int paginas[]= {
                R.layout.activity_scrolling1,
                R.layout.activity_scrolling2,
                R.layout.activity_scrolling3,
                R.layout.activity_scrolling4

        };

        viewPager.setAdapter(new PagerAdapter() {

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(paginas[position],container, false);
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return paginas.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = (View) object;
                container.removeView(view);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: "+position );
                if(position == 3){
                    next.setText("Login");
                }
                else{
                    next.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        next.setOnClickListener(view -> {
            int current = viewPager.getCurrentItem() ;

            if(current<paginas.length - 1){
                viewPager.setCurrentItem(current + 1);
            }else{
                launchDashboard();
            }
        });



        skip.setOnClickListener(view -> {
            startActivity(new Intent(ScrollingActivity.this, Login.class));
        });


    }

    private void launchDashboard() {
        startActivity(new Intent(ScrollingActivity.this, Login.class));
    }


}