package com.example.ibnsina.listviewexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText txt1,txt2,txt3,txt4;
    Button b1,b2,b3,b4;

    ListView lview;

    sqlexample mysql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (EditText)findViewById(R.id.editText);
        txt2 = (EditText)findViewById(R.id.editText2);
        txt3 = (EditText)findViewById(R.id.editText3);
        txt4 = (EditText)findViewById(R.id.editText4);
        b1 = (Button) findViewById(R.id.button);

        lview = (ListView)findViewById(R.id.ListView);

        mysql =new sqlexample(this);


        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                boolean checker = mysql.AddToTable(txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString(),txt4.getText().toString());
                if(checker == true)
                {
                    Toast.makeText(MainActivity.this,"Sucessfully Insert",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Noottttttt Insert",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void Display(View view)
    {
        Cursor res = mysql.viewdata();
        if(res.getCount() == 0)
        {
            Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG).show();
            return;
        }

        res.moveToFirst();

        String[] name = new String[res.getCount()];
        int n = 0;
        do{
            name[n] = res.getString(1).toString();
            n = n + 1;
        }

        while(res.moveToNext());
        ListAdapter listAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,name);
        lview.setAdapter(listAdapter);
    }
}


