package com.hado.indicator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hado.indicatorlibrary.IndicatorView;
import com.hado.indicatorlibrary.PagesLessException;
import com.hado.progressbarlib.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    IndicatorView indicatorView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        indicatorView = (IndicatorView) findViewById(R.id.indicator);
        PagerAdapter adapter = new PagerAdapter(this);
        viewPager.setAdapter(adapter);
        try {
            indicatorView.setViewPager(viewPager);
        } catch (PagesLessException e) {
            e.printStackTrace();
        }

        new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                for (int i = 0; i <= 100; i++) {
                    publishProgress(i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                progressBar.setProgress(values[0]);
            }
        }.execute();



        progressBar.setProgress(46);

    }
}
