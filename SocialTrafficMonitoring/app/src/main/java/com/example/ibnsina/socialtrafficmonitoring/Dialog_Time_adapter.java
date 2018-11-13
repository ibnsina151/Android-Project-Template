package com.example.ibnsina.socialtrafficmonitoring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ibnsina on 1/13/18.
 */

public class Dialog_Time_adapter extends BaseAdapter {
    private Context mContext;
    private String[] time_txt;

    private LayoutInflater mInflater;

    public Dialog_Time_adapter(Context c,String[] time_txt) {
        //mInflater = LayoutInflater.from(c);

        this.mContext = c;
        this.time_txt = time_txt;

    }

    public int getCount() {
        return time_txt.length;
    }

    public Object getItem(int position) {
        return time_txt[position];
    }
    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.waiting_dialog_item,null);
                   }
        //get View;
        TextView time_view =(TextView)convertView.findViewById(R.id.wating_item_textview);

        //Assist data;
        time_view.setText(time_txt[position]);

//        ViewHolder holder;
//
//        if (convertView == null) {  // if it's not recycled,
//            convertView = mInflater.inflate(R.layout.waiting_dialog_item, null);
//            convertView.setLayoutParams(new GridView.LayoutParams(100,35));
//            holder = new ViewHolder();
//            holder.title = (TextView) convertView.findViewById(R.id.wating_item_textview);
//            //holder.icon = (ImageView)convertView.findViewById(R.id.categoryImage);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        //holder.icon.setAdjustViewBounds(true);
//        //holder.icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        //holder.icon.setPadding(5, 5, 5, 5);
//        holder.title.setPadding(5, 5, 5, 5);
//        holder.title.setText(categoryContent[position]);
//
//
//        //holder.icon.setImageResource(mThumbIds[position]);
        return convertView;
    }

    class ViewHolder {
        TextView title;
        //ImageView icon;
    }
    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.ic_cancel_black_24dp, R.drawable.ic_cancel_black_24dp,R.drawable.ic_cancel_black_24dp,
//            R.drawable.ic_cancel_black_24dp,R.drawable.ic_cancel_black_24dp, R.drawable.ic_cancel_black_24dp
//    };

//}
    private String[] categoryContent = {
            "1 Minites", "Restuarants","shopping",
            "theatre","train", "taxi",
    };


}