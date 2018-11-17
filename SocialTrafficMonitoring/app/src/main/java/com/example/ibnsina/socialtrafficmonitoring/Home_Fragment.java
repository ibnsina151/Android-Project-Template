package com.example.ibnsina.socialtrafficmonitoring;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibnsina on 1/8/18.
 */

public class Home_Fragment extends Fragment implements RecyclerViewAdapter.OnItemClicked {


    //a list to store all the products
    List<post_item_cecycle> post_itemList;

    //the recyclerview
    RecyclerView recyclerView;



    View viewer;

    public Home_Fragment(){

    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        viewer = inflater.inflate(R.layout.activity_fragment_home, container,false);
        //viewer2 = inflater.inflate(R.layout.activity_fragment_statistics,container,false);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) viewer.findViewById(R.id.post_viewer_recycle);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //initializing the productlist
        post_itemList = new ArrayList<>();

        String[] user_name = {"Ibn Sina Munim"};
        String[] date_time = {"22-01-17, 4:00 PM"};
        int[] user_image = {R.drawable.ic_home_black_home_symbol};
        String[] traffic_location = {"Mirpur-10"};
        int[] traffic_level = {R.drawable.ic_traffic_light_post_yell};
        String[] traffic_waittime = {"8:00 Minutes"};
        String[] traffic_comments = {"Terrible traffic jam at Mirpur Section-10 roundabout has become a headache for the residents of the Mirpur-Pallabi area, one of the most densely populated areas of the city"};

        int count_ = user_image.length;

        try {
            //load data.
            for (int i = 0; i < 1; i++) {
                post_itemList.add(new post_item_cecycle(user_name[count_], user_image[count_], date_time[count_], traffic_location[count_],
                        traffic_waittime[count_], traffic_level[count_], traffic_comments[count_]));
                Log.d("Whats the Problem", "" + count_ + "");
                count_++;
            }
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, post_itemList);
            //setting adapter to recyclerview
            recyclerView.setAdapter(adapter);
            adapter.setOnClick(this);
        }

        catch (Exception ex){
            Toast.makeText(viewer.getContext(), "Server Not Connect", Toast.LENGTH_SHORT).show();
        }
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        return viewer;
    }

    @Override
    public void onItemClick(int position) {

    }




}
