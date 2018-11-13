package com.example.ibnsina.arraylist_value_pass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Qabir q1 = new Qabir(22, "KQ",700);
        Qabir q2 = new Qabir(23, "K Q",1150);
        Qabir q3 = new Qabir(24, "K_Q",1200);
        Qabir q4 = new Qabir(213,"K_R",3000);

        ArrayList<Qabir> list = new ArrayList<Qabir>();

        list.add(q1);
        list.add(q2);
        list.add(q3);
        list.add(q4);

        Intent in = new Intent(this, SubActivity.class);
        in.putExtra("obj", arrayListToJSON(list));

        startActivity(in);
    }



    private String arrayListToJSON(ArrayList<Qabir> al) {
        JSONArray array = new JSONArray();
        try {
            //for (int i = 0; i < al.size(); i++) {

                array.put(new JSONObject(al.get(0).toJSON()));

               // array.put(new JSONObject(al.get(i).name));
//                array.put(new JSONObject(String.valueOf(al.get(i).price)));
//                array.put(new JSONObject(String.valueOf(al.get(i).age)));
//                Log.d("total array", String.valueOf(al.get(i).price));
//                Log.d("Total Price",al.get(i).name);
            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array.toString();
    }
}
