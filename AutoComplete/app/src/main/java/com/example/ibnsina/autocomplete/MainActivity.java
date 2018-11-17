package com.example.ibnsina.autocomplete;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.app.AlertDialog;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView text;
    MultiAutoCompleteTextView text2;
    String [] Language = {"Java","Android","Apple","IBM_Corporation","Language"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Auto Complete Method.
        text = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        text2 = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Language);
        text.setAdapter(adapter);
        text.setThreshold(1);
        text2.setAdapter(adapter);
        text2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //End


        //Font Change.
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/font.ttf");
        text.setTypeface(typeface);
    }

    //new Activity
    public void next(View view)
    {
        //Button next =(Button)findViewById(R.id.btnnext);
        Intent intent = new Intent(this, Audio_mode.class);
        startActivity(intent);
    }

    public void clip(View view)
    {
        Intent clipb = new Intent(this,clipboardtutorial.class);
        startActivity(clipb);;
    }

    //Alart Dialog Method.
    public void open(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are u Sure, U want to Make Dicision");
        alert.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "You Click Yes Button", Toast.LENGTH_LONG).show();
                    }
                });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this,"You Click No Button",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    //End
}
