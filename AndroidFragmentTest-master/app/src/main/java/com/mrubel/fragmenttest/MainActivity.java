package com.mrubel.fragmenttest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout my_tl;
    private ViewPager my_vp;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_vp = (ViewPager) findViewById(R.id.my_view_pager);
        my_tl = (TabLayout) findViewById(R.id.my_tabs);

        setUpMyViewPager(my_vp);
        my_tl.setupWithViewPager(my_vp);

    }



    void setUpMyViewPager(ViewPager vp){

        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.addMyFragment(new FragmentOne(), "AndroidLIme");
        vpa.addMyFragment(new FragmentTwo(), "M Rubel");
        vpa.addMyFragment(new FragmentThree(), "ImRubel");

        vp.setAdapter(vpa);

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> my_list = new ArrayList<Fragment>();
        private final List<String> my_titles = new ArrayList<String>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return my_list.get(position);
        }


        @Override
        public int getCount() {
            return my_list.size();
        }

        void addMyFragment(Fragment f, String title){
            my_list.add(f);
            my_titles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return my_titles.get(position);
        }


    }


}
