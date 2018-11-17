package com.example.ibnsina.featch_multiple_value;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    Typeface font;
    public static EditText txtListChild = null;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_list_item, null);
        }

        txtListChild = (EditText) convertView
                .findViewById(R.id.expEdt);
        ImageView _expandcountry = (ImageView) convertView.findViewById(R.id.exapandCountry);
        Spinner _spnCountry = (Spinner) convertView.findViewById(R.id.country_spinner);
        ArrayList<String> _countyArrList = new ArrayList<String>();
        _countyArrList.add("India");
        _countyArrList.add("USA");
        _countyArrList.add("UAE");
        _countyArrList.add("South Africa");
        ArrayAdapter<String> _colorAdap = new ArrayAdapter<String>(_context,
                R.layout.spinner_country, _countyArrList);
        _colorAdap.setDropDownViewResource(R.layout.spinner_country);
        _spnCountry.setAdapter(_colorAdap);
        _spnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                /*if (_transferTo[position].equalsIgnoreCase("Select location")) {
                    _transferToSpinner.setSelection(position);
                    _location = _transferToSpinner.getSelectedItem().toString();
                } else {
                    _location = _transferToSpinner.getSelectedItem().toString();
                    //System.out.println("Selected location" + _location);
                    _transferToSpinner.setSelection(position);
                }*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        txtListChild.setText(childText);
        if(childPosition==0)
        {
            txtListChild.setHint("Name");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);

        }
        else if(childPosition==1)
        {
            txtListChild.setHint("Last Name");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }
        else if(childPosition==2)
        {
            txtListChild.setHint("Address Line 1");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }
        else if(childPosition==3)
        {
            txtListChild.setHint("Address Line 2");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }
        else if(childPosition==4)
        {
            txtListChild.setHint("Phone");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }

        else if(childPosition==5)
        {
            txtListChild.setHint("City");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }
        else if(childPosition==6)
        {
            txtListChild.setHint("State");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }
        else if(childPosition==7)
        {
            txtListChild.setHint("Zip Code");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            txtListChild.setVisibility(View.VISIBLE);
            _expandcountry.setVisibility(View.GONE);
            _spnCountry.setVisibility(View.GONE);
        }
        else if(childPosition==8)
        {
            txtListChild.setHint("Country");
            txtListChild.setHintTextColor(_context.getResources().getColor(R.color.grey));
            _expandcountry.setVisibility(View.VISIBLE);
            _spnCountry.setVisibility(View.VISIBLE);
            txtListChild.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        System.out.println("group position"+groupPosition);
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_list_group, null);
        }
        Typeface.createFromAsset(_context.getAssets(),"century_gothic.ttf");

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.addressTxt);
        LinearLayout _panelLayout = (LinearLayout) convertView.findViewById(R.id.exp_maingrp_rl);
        ImageView _rightIcon = (ImageView) convertView.findViewById(R.id.rightBtn);
        ImageView _openCloseBtn = (ImageView) convertView.findViewById(R.id.openCloseBtn);


        int panelBackId = isExpanded ?  R.drawable.panel_active_tab: R.drawable.panel_inactive ;
        _panelLayout.setBackgroundResource(panelBackId);


        int textResourceId = isExpanded ? _context.getResources().getColor(R.color.white) : _context.getResources().getColor(R.color.grey) ;
        lblListHeader.setTextColor(textResourceId);
        lblListHeader.setTypeface(font);
        lblListHeader.setText(headerTitle);

        int imageResourceId = isExpanded ? R.drawable.right_white : R.drawable.right_black  ;
        _rightIcon.setImageResource(imageResourceId);

        int imageOpenCloseId = isExpanded ? R.drawable.arrow_bottom : R.drawable.forward_iv_black  ;
        _openCloseBtn.setImageResource(imageOpenCloseId);


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}