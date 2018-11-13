package com.example.ibnsina.expendablelistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by ibnsina on 7/4/17.
 */

public class ExampleAdapter implements ExpandableListAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public ExampleAdapter(Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = null;
        if(groupPosition == 0) {
            v = View.inflate(context, R.layout.expandable_child_layout, null);
            TextView txtView = (TextView) v.findViewById(R.id.txtChld1);
            txtView.setText("Green");
            txtView.setTextSize(15f);
            txtView.setBackgroundColor(Color.GREEN);
        }
        if(groupPosition == 1) {
            v = View.inflate(context, R.layout.expandable_child_1_layout, null);
        }
        if(groupPosition == 2) {
            v = View.inflate(context, R.layout.expandable_child_2_layout, null);
        }
        if(groupPosition == 3) {
            v = View.inflate(context, R.layout.expandable_child_layout, null);
            TextView txtView = (TextView) v.findViewById(R.id.txtChld1);
            txtView.setText("Purple");
            txtView.setTextSize(15f);
        }
        v.invalidate();
        return v;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return 4;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView.inflate(context, R.layout.expandable_group_layout, null);
        TextView txtView = (TextView) v.findViewById(R.id.txt1);
        if(groupPosition == 0) {
            txtView.setText("Group Head 1");
            txtView.setTextSize(15f);
        }
        if(groupPosition == 1) {
            txtView.setText("Group Head 2");
            txtView.setTextSize(15f);
        }
        if(groupPosition == 2) {
            txtView.setText("Group Head 3");
            txtView.setTextSize(15f);
        }
        if(groupPosition == 3) {
            txtView.setText("Group Head 4");
            txtView.setTextSize(15f);
        }
        v.invalidate();
        return v;

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }
}
