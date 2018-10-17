package com.chris.moviedatabase.activities.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.chris.moviedatabase.R;
import com.chris.moviedatabase.adapters.PageAdapter;
import com.chris.moviedatabase.fragments.ListMov.view.ListMovie;
import com.chris.moviedatabase.fragments.Favoritos.view.ListMovieFav;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabLayout1) TabLayout tabLayout1;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setUpViewPager();

    }

    private ArrayList<Fragment> agregarFragments(){

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListMovie());
        fragments.add(new ListMovieFav());

        return fragments;
    }

    //poner en orbita los fragments
    private void setUpViewPager(){

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout1.setupWithViewPager(viewPager);

        //tabLayout.getTabAt(0).setIcon(R.drawable.like);
        //tabLayout.getTabAt(1).setIcon(R.drawable.like);
        tabLayout1.getTabAt(0).setText("Peliculas");
        tabLayout1.getTabAt(1).setText("Favoritos");



    }
}
