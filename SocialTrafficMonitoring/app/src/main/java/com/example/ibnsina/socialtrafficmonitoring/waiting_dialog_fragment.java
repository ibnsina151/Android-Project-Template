package com.example.ibnsina.socialtrafficmonitoring;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ibnsina on 1/17/18.
 */


public class waiting_dialog_fragment extends DialogFragment {


    GridView grid;
    ImageView btn_cl;
    Button btn_signal_waiting_time;

    String[] time_txt={"Free Road","1 Minutes","2 Minutes","3 Minutes","4 Minutes","5 Minutes","6 Minutes",
            "7 Minutes","8 Minutes","9 Minutes","10 Minutes","11 Minutes","12 Minutes","15 Minutes"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.wating_dialog_viewer,null);

        final View info_view = inflater.inflate(R.layout.activity_fragment_information,null);

        grid = (GridView)rootview.findViewById(R.id.waiting_dialog_grid);
        btn_cl = (ImageView)rootview.findViewById(R.id.waiting_dialog_close);

        btn_signal_waiting_time = (Button)info_view.findViewById(R.id.btm_signal_waiting_time);

        //getDialog().setTitle("Selection Waiting Time");

        Dialog_Time_adapter adapter =new Dialog_Time_adapter(getActivity(),time_txt);
        grid.setAdapter(adapter);

        btn_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //btn_signal_waiting_time.setText("show hide");

                Toast.makeText(info_view.getContext(), "Show"+position, Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }


}
