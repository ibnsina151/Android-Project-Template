package com.example.ibnsina.socialtrafficmonitoring;

/**
 * Created by ibnsina on 10/22/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView user_Name;
    public ImageView user_Photo;
    public TextView date_Time;
    public TextView traffic_location;
    public ImageView traffic_level;
    public TextView traffic_waiting;
    public TextView traffic_comment;

    public LinearLayout recycle_view;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        user_Name = (TextView)itemView.findViewById(R.id.txt_postview_username);
        user_Photo = (ImageView)itemView.findViewById(R.id.img_postview_userimage);
        date_Time = (TextView)itemView.findViewById(R.id.txt_postview_datetime);
        traffic_location = (TextView) itemView.findViewById(R.id.txt_postview_area);
        traffic_level = (ImageView)itemView.findViewById(R.id.img_postview_level);
        traffic_waiting = (TextView)itemView.findViewById(R.id.txt_post_view_waittime);
        traffic_comment = (TextView) itemView.findViewById(R.id.txt_postview_comment);

        recycle_view = (LinearLayout) itemView.findViewById(R.id.post_viewHolder);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();



    }
}
