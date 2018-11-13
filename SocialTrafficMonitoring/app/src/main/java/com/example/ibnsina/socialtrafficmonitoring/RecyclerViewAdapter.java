package com.example.ibnsina.socialtrafficmonitoring;

/**
 * Created by ibnsina on 10/22/17.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {


    private List<post_item_cecycle> itemList;
    private Fragment context;

    private OnItemClicked onClick;

    public interface OnItemClicked{

        void onItemClick(int position);
    }



    public RecyclerViewAdapter(Home_Fragment context, List<post_item_cecycle> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_viewer,parent, Boolean.parseBoolean(null));
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }



    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {

        holder.recycle_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });

        holder.user_Name.setText(itemList.get(position).getUserName());
        holder.user_Photo.setImageResource(itemList.get(position).getUserImage());
        holder.date_Time.setText(itemList.get(position).getDateTime());
        holder.traffic_location.setText(itemList.get(position).getTrafficLocation());
        holder.traffic_level.setImageResource(itemList.get(position).getTrafficLevel());
        holder.traffic_waiting.setText(itemList.get(position).getTrafficWating());
        holder.traffic_comment.setText(itemList.get(position).getTrafficComment());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}
