package com.example.ibnsina.fragmenttutorial;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout my_tl;
    private ViewPager my_vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_tl = (TabLayout)findViewById(R.id.tab_list);
        my_vp = (ViewPager)findViewById(R.id.main_pageviewer);

        setupMyviewPager(my_vp);
        my_tl.setupWithViewPager(my_vp);

    }


    void setupMyviewPager(ViewPager vp){
        viewpagerAdapter vpa = new viewpagerAdapter(getSupportFragmentManager());
        vpa.addMyFragment(new fragment_one(), "First");
        vpa.addMyFragment(new fragment_tow(), "Second");
        vpa.addMyFragment(new fragment_three(), "Third");

        vp.setAdapter(vpa);

    }

    public class viewpagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> my_fl = new ArrayList<Fragment>();
        private final List<String> my_title = new ArrayList<String>();

        public viewpagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int position) {
            return my_fl.get(position);
        }

        @Override
        public int getCount() {
            return my_fl.size();
        }

        void addMyFragment(Fragment f,String s)
        {
            my_fl.add(f);
            my_title.add(s);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return my_title.get(position);
        }
    }

}
