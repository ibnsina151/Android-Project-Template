package com.example.ibnsina.february;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class gallery extends AppCompatActivity {

    ViewPager viewPager;
    Custom_swinp_pager adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new Custom_swinp_pager(this);
        viewPager.setAdapter(adapter);



    }
}
