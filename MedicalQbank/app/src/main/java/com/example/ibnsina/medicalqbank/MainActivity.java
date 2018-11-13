package com.example.ibnsina.medicalqbank;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    ExpandableListAdapter_nav listAdapter_nav;
    ExpandableListView expListView_nav;
    List<String> listDataHeader_nav;
    HashMap<String, List<String>> listDataChild_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Expandable list view............................................

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);



        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //indicator layout for group title
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        DisplayMetrics metrics2 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics2);
        int width2 = metrics2.widthPixels;


        expListView.setIndicatorBounds(width - GetPixelFromDips(50), width - GetPixelFromDips(10));
        expListView.setChildIndicatorBounds(width2 - GetPixelFromDips_group_nav(30), width2 - GetPixelFromDips_group_nav(20));



        //Custom Navigatoin bar details.....................

        expListView_nav = (ExpandableListView) findViewById(R.id.simpleExpandableListView2);

        prepareListData_nav();
        listAdapter_nav = new ExpandableListAdapter_nav(this, listDataHeader_nav, listDataChild_nav);
        // setting list adapter
        expListView_nav.setAdapter(listAdapter_nav);
        //indicator layout for group title
        DisplayMetrics metrics_nav = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics_nav);
        int width_nav = metrics.widthPixels;
        //expListView_nav.setIndicatorBounds(width_nav - GetPixelFromDips_group_nav(10),width_nav-GetPixelFromDips_group_nav(10));


        expListView_nav.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Listview Group expanded listener
        expListView_nav.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView_nav.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listenerenableExpandableList
        expListView_nav.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                // Temporary code:

                // till here
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader_nav.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader_nav.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });


    }

    //Custom Navigation bar details.


    // Nav data collection.
    private void prepareListData_nav() {

        listDataHeader_nav =new ArrayList<>() ;
        listDataChild_nav= new HashMap<String, List<String>>();


        // Adding child data
        listDataHeader_nav.add("Notes");
        listDataHeader_nav.add("Flashcard");
        listDataHeader_nav.add("Seip-assesment");
        listDataHeader_nav.add("My Contents");
        listDataHeader_nav.add("Dynamic Exam");
        listDataHeader_nav.add("Statistics");
        listDataHeader_nav.add("About");


        // Adding child data
        List<String> Notes = new ArrayList<String>();
        Notes.add("General");
        Notes.add("Systemic");
        Notes.add("Others");
        Notes.add("Questions from last 5 years -15 Acts");
        Notes.add("Roddie");
        Notes.add("Questions Roddie");
        Notes.add("Questions Simddy");
        Notes.add("Questions Lumley");

        List<String> General = new ArrayList<>();
        General.add("Bacteriology");
        General.add("Virology");
        General.add("Mycology");

        List<String> Flashcard = new ArrayList<String>();
        Flashcard.add("yt");

        List<String> Seipassesment = new ArrayList<String>();
        Seipassesment.add("z1");




        listDataChild_nav.put(listDataHeader_nav.get(0), Notes); // Header, Child data
        listDataChild_nav.put(listDataHeader_nav.get(1), Flashcard);
        listDataChild_nav.put(listDataHeader_nav.get(2), Seipassesment);
        listDataChild_nav.put(listDataHeader_nav.get(3), Notes); // Header, Child data
        listDataChild_nav.put(listDataHeader_nav.get(4), Flashcard);
        listDataChild_nav.put(listDataHeader_nav.get(5), Seipassesment);
        listDataChild_nav.put(listDataHeader_nav.get(6), Seipassesment);


    }


    //End Custom navigation bar.




    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    public int GetPixelFromDips_group_nav(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

     /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Test Mode");
        listDataHeader.add("Question Mode");
        listDataHeader.add("Subjects");
        listDataHeader.add("Systems");
        listDataHeader.add("Question Count");

        // Adding child data
        List<String> testmode = new ArrayList<String>();
        testmode.add("Timed");
        testmode.add("Untimed");
        testmode.add("Tutor");
        testmode.add("Timed Tutor");

        List<String> questionmode = new ArrayList<String>();
        questionmode.add("The Conjuring");
        questionmode.add("Despicable Me 2");
        questionmode.add("Turbo");
        questionmode.add("Grown Ups 2");
        questionmode.add("Red 2");
        questionmode.add("The Wolverine");

        List<String> subjects = new ArrayList<String>();
        subjects.add("2 Guns");
        subjects.add("The Smurfs 2");
        subjects.add("The Spectacular Now");
        subjects.add("The Canyons");
        subjects.add("Europa Report");

        List<String> systems = new ArrayList<String>();
        systems.add("2 Guns");
        systems.add("The Smurfs 2");
        systems.add("The Spectacular Now");
        systems.add("The Canyons");
        systems.add("Europa Report");

        List<String> questioncount = new ArrayList<String>();
        questioncount.add("2 Guns");
        questioncount.add("The Smurfs 2");
        questioncount.add("The Spectacular Now");
        questioncount.add("The Canyons");
        questioncount.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), testmode); // Header, Child data
        listDataChild.put(listDataHeader.get(1), questionmode);
        listDataChild.put(listDataHeader.get(2), subjects);
        listDataChild.put(listDataHeader.get(3),systems);
        listDataChild.put(listDataHeader.get(4),questioncount);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            // Handle the camera action
        } else if (id == R.id.nav_flashcard) {

        } else if (id == R.id.nav_mycontents) {

        } else if (id == R.id.nav_seip) {

        } else if (id == R.id.nav_mycontents) {

        } else if (id == R.id.nav_dyexam) {

        } else if (id == R.id.nav_stexam) {

        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
}
