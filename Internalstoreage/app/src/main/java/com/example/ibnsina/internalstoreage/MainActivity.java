package com.example.ibnsina.internalstoreage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    EditText ed1;
    TextView tv;
    String data;
    private String file = "mydata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 =(Button)findViewById(R.id.btnsave);
        btn2 = (Button)findViewById(R.id.btnload);

        ed1 = (EditText)findViewById(R.id.editText);

        tv  = (TextView)findViewById(R.id.textView2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data = ed1.getText().toString();
                try
                {
                    FileOutputStream fout = openFileOutput(file,MODE_WORLD_READABLE);
                    fout.write(data.getBytes());
                    fout.close();
                    Toast.makeText(MainActivity.this, "Data Save", Toast.LENGTH_SHORT).show();
                }
                catch(Exception ex)
                {
                    ex.getStackTrace();
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(file);
                    int c;
                    String temp="";
                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    tv.setText(temp);
                    Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }


            }
        });

    }
}
