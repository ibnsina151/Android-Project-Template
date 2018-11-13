package com.mrubel.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mosharrofrubel on 10/23/16.
 */
public class FragmentTwo extends Fragment {

    View v2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v2 = inflater.inflate(R.layout.fragment_two_layout, container, false);



        return v2;
    }
}
