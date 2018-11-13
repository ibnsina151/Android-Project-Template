package com.example.ibnsina.expendablelistview;

import android.app.ExpandableListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExampleAdapter adapter = new ExampleAdapter(this, getLayoutInflater());
        setListAdapter(adapter);

    }
}
