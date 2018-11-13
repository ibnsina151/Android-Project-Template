package com.example.ibnsina.socialtrafficmonitoring;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout my_tl;
    private ViewPager my_vp;

    private int[] tabIcons = {
            R.drawable.ic_home_black_home_symbol,
            R.drawable.ic_semaphore_of_signal_lights,
            R.drawable.ic_backpacker_journey_tab,
            R.drawable.ic_graph_statistic,
            R.drawable.ic_speech_bubbles_post_option
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this.setTitle("দেখেচলো");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.inflateMenu(R.menu.main);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        my_tl = (TabLayout)findViewById(R.id.tab_list);
        my_vp = (ViewPager)findViewById(R.id.main_pageviewer);

        setupMyviewPager(my_vp);
        my_tl.setupWithViewPager(my_vp);
        setupTabIcons();

        // getActivity() will hand over the context to the method
        // if you call this inside an activity, simply replace getActivity() by "this"
        if(!isConnected(this)) buildDialog(this).show();
        else {
            // we have internet connection, so it is save to connect to the internet here

        }

    }


    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet connection.");
        builder.setMessage("You have no internet connection");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        return builder;
    }

    private void setupTabIcons() {
        my_tl.getTabAt(0).setIcon(tabIcons[0]);
        //my_tl.getTabAt(1).setIcon(tabIcons[1]);
        my_tl.getTabAt(1).setIcon(tabIcons[2]);
        my_tl.getTabAt(2).setIcon(tabIcons[3]);
        my_tl.getTabAt(3).setIcon(tabIcons[4]);
    }

    void setupMyviewPager(ViewPager vp){
        viewpagerAdapter vpa = new viewpagerAdapter(getSupportFragmentManager());
        vpa.addMyFragment(new Home_Fragment(), "Home");
        //vpa.addMyFragment(new Signal_Fragment(),"Signal");
        vpa.addMyFragment(new Journey_Fragment(),"Journey");
        vpa.addMyFragment(new Statistics_Fragment(), "Statistics");
        vpa.addMyFragment(new Information_Fragment(), "Post");
        vp.setAdapter(vpa);

    }

    public class viewpagerAdapter extends FragmentPagerAdapter {

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
            //return null;
            return my_title.get(position);}

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btn_search_recipes) {

        }
        else if(id == R.id.btn_about_recipes){
            CustomDialogClass_about cds = new CustomDialogClass_about(this);
            cds.show();

        }
        else if(id == R.id.btn_share_someone){

        }
        else if (id == R.id.btn_help){

        }

        return super.onOptionsItemSelected(item);
    }


}
