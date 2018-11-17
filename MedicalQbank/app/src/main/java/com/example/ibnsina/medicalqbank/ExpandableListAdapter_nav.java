package com.example.ibnsina.medicalqbank;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ibnsina on 7/5/17.
 */

public class ExpandableListAdapter_nav extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter_nav(Context context, List<String> listDataHeader,
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
            convertView = infalInflater.inflate(R.layout.childitem_nav, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.ch_item1_nav);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
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
            convertView = infalInflater.inflate(R.layout.groupitem_nav, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.txtheading_nav);
        lblListHeader.setTypeface(null, Typeface.NORMAL);
        lblListHeader.setText(headerTitle);

        TextView listTitleTextArrowView = (TextView) convertView
                .findViewById(R.id.listTitleArrow);
        listTitleTextArrowView.setTypeface(null, Typeface.NORMAL);
        listTitleTextArrowView.setTypeface(FontManager.getTypeface(_context, FontManager.FONTAWESOME));

        // set icons for menu items
        TextView listTitleTextIconView = (TextView) convertView
                .findViewById(R.id.listTitleIcon);
        listTitleTextIconView.setTypeface(null, Typeface.NORMAL);
        listTitleTextIconView.setTypeface(FontManager.getTypeface(_context, FontManager.FONTAWESOME));

        if (groupPosition == 0)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_glass));
        else if (groupPosition == 1)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_music));
        else if (groupPosition == 2)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_search));
        else if (groupPosition == 3)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_envelope_o));
        else if (groupPosition == 4)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_music));
        else if (groupPosition == 5)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_search));
        else if (groupPosition == 6)
            listTitleTextIconView.setText(_context.getResources().getString(R.string.fa_envelope_o));


        // set arrow icons for relevant itemsfont
        if (groupPosition == 0 || groupPosition == 1 || groupPosition == 2 || groupPosition == 3|| groupPosition == 4 || groupPosition == 5 ) {
            if (!isExpanded)
                listTitleTextArrowView.setText(_context.getResources().getString(R.string.fa_chevron_right));
            else
                listTitleTextArrowView.setText(_context.getResources().getString(R.string.fa_chevron_down));
        } else {
            listTitleTextArrowView.setText("");

        }

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
