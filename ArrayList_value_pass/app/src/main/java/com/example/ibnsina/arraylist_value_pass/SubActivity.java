package com.example.ibnsina.arraylist_value_pass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        TextView txt1 =(TextView)findViewById(R.id.txt123);
        ArrayList<Qabir> list =new ArrayList<Qabir>();
        list = getObjectList(getIntent().getStringExtra("obj"));
        Log.d("Activity 2", "" +list.size());

    }

    private ArrayList<Qabir> getObjectList(String st) {

        ArrayList<Qabir> list = new ArrayList<Qabir>();
        JSONArray array = null;
        try {
            array = new JSONArray(st);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < array.length(); i++) {
            try {
                Qabir q= new Qabir();
                q.initilizeWithJSONString(""+array.get(i));
                list.add(q);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
