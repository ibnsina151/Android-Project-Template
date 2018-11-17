package com.example.ibnsina.gridview_itembackgourndcolor;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    List<String> ItemsList;
    String selectedItem;
    TextView GridViewItems,BackSelectedItem;
    int backposition = -1;
    String[] itemsName = new String[]{
            "ONE",
            "TWO",
            "THREE",
            "FOUR",
            "FIVE",
            "SIX",
            "SEVEN",
            "EIGHT",
            "NINE",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView)findViewById(R.id.gridView1);
        ItemsList = new ArrayList<String>(Arrays.asList(itemsName));
        gridView.setAdapter(new TextAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub



                selectedItem = parent.getItemAtPosition(position).toString();
                GridViewItems = (TextView) view;
                GridViewItems.setBackgroundColor(Color.parseColor("#814f00"));
                GridViewItems.setTextColor(Color.parseColor("#fdfcfa"));
                BackSelectedItem = (TextView) gridView.getChildAt(backposition);

                if (backposition != -1)
                {

                    BackSelectedItem.setSelected(false);

                    BackSelectedItem.setBackgroundColor(Color.parseColor("#fbdcbb"));

                    BackSelectedItem.setTextColor(Color.parseColor("#040404"));
                }

                backposition = position;

            }
        });
    }

    private class TextAdapter extends BaseAdapter
    {

        Context context;

        public TextAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public int getCount() {

            return itemsName.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub

            return itemsName[position];
        }

        @Override
        public long getItemId(int position) {

            // TODO Auto-generated method stub

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            TextView text = new TextView(this.context);

            text.setText(itemsName[position]);

            text.setGravity(Gravity.CENTER);

            text.setBackgroundColor(Color.parseColor("#fbdcbb"));

            text.setTextColor(Color.parseColor("#040404"));

            text.setLayoutParams(new GridView.LayoutParams(144, 144));


            return text;

        }


    }
}
