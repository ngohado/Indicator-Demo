package com.hado.indicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hado.indicatorlibrary.IndicatorView;
import com.hado.indicatorlibrary.PagesLessException;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    IndicatorView indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicatorView = (IndicatorView) findViewById(R.id.indicator);
        PagerAdapter adapter = new PagerAdapter(this);
        viewPager.setAdapter(adapter);
        try {
            indicatorView.setViewPager(viewPager);
        } catch (PagesLessException e) {
            e.printStackTrace();
        }

    }
}
