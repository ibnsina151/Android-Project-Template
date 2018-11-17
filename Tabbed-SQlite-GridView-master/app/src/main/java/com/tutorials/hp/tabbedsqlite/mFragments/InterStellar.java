package com.tutorials.hp.tabbedsqlite.mFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import com.tutorials.hp.tabbedsqlite.R;
import com.tutorials.hp.tabbedsqlite.mDB.DBAdapter;
import com.tutorials.hp.tabbedsqlite.mModel.Spacecraft;



public class InterStellar extends Fragment {

    GridView gv;
    Button refreshBtn;
    ArrayAdapter<Spacecraft> adapter;

    public static InterStellar newInstance() {
        return new InterStellar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.interstellar, null);

        initializeViews(rootView);
        loadData();

        return rootView;
    }

    /*
    INITIALIZE VIEWS
     */

    private void initializeViews(View rootView)
    {
        gv= (GridView) rootView.findViewById(R.id.interstellar_GV);
        refreshBtn= (Button) rootView.findViewById(R.id.interstellarRefresh);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
    }



    /*
    LOAD DATA
     */

    private void loadData()
    {
        DBAdapter db=new DBAdapter(getActivity());
        adapter=new ArrayAdapter<Spacecraft>(getActivity(),android.R.layout.simple_list_item_1,db.retrieveSpacecrafts(this.toString()));
        gv.setAdapter(adapter);
    }



}
