package com.example.ibnsina.socialtrafficmonitoring;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ibnsina on 1/8/18.
 */

public class Statistics_Fragment extends Fragment {


    View viewer,viewer2;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        viewer = inflater.inflate(R.layout.activity_fragment_statistics, container,false);
        //viewer2 = inflater.inflate(R.layout.activity_fragment_statistics,container,false);

        return viewer;
    }
}
