package com.example.ibnsina.myotheractivity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void newactiv(View view)
    {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
    public void alartdialog(View view)
    {
        final AlertDialog.Builder alertbuilder = new AlertDialog.Builder(MainActivity.this);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View inf = inflater.inflate(R.layout.customeview,null);

        final EditText txt1 = (EditText)inf.findViewById(R.id.editText3);
        final EditText txt2 = (EditText)inf.findViewById(R.id.editText5);
        final Button btn1 = (Button) inf.findViewById(R.id.button4);

        alertbuilder.setView(inf);

        final AlertDialog alert = alertbuilder.create();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = txt1.getText().toString() + " "+ txt2.getText().toString();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                alert.dismiss();
            }
        });

        alert.show();

    }
}
