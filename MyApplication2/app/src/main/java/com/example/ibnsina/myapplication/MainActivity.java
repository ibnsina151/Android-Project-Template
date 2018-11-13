package com.example.ibnsina.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt1,txt2,txt3,txt4;
    Button b1,b2,b3;
    TextView ltxt1;

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
        b2 =(Button)findViewById(R.id.button3);
        ltxt1 = (TextView) findViewById(R.id.textView);
        b3 =(Button)findViewById(R.id.button4);

        mysql =new sqlexample(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
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

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata();;
            }
        });

    }

    public void showdata()
    {
        Cursor res = mysql.viewdata();
        if(res.getCount() == 0)
        {
            Toast.makeText(MainActivity.this,"Data Not Found",Toast.LENGTH_LONG).show();
            return;
        }

        res.moveToFirst();

        StringBuffer stringBuffer = new StringBuffer();
        do {
            stringBuffer.append("ID: "+res.getString(0)+"\n");
            stringBuffer.append("First Name: "+res.getString(1)+"\n");
            stringBuffer.append("Last Name: "+res.getString(2)+"\n");
            stringBuffer.append("@Email: "+res.getString(3)+"\n\n");


        }
        while(res.moveToNext());

        Display(stringBuffer.toString());
    }

    public void Display(String data)
    {
        ltxt1.setText(data);
    }

    public void updatetade(View view)
    {
        boolean checker = mysql.updatetade(txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString(),txt4.getText().toString());
        if(checker == true)
        {
            Toast.makeText(MainActivity.this,"Sucessfully Update",Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(MainActivity.this,"Noottttttt Update",Toast.LENGTH_LONG).show();
        }

    }
    public void deletevalue(View view)
    {
        int checker = mysql.deleted(txt1.getText().toString());
        if(checker > 0)
        {
            Toast.makeText(MainActivity.this,"Sucessfully Delete",Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(MainActivity.this,"Noottttttt Delete",Toast.LENGTH_LONG).show();
        }
    }
}
