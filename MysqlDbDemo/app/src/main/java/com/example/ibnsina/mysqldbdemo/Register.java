package com.example.ibnsina.mysqldbdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity {

    EditText ET_name,ET_user_name,ET_user_pass;
    String name,user_name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ET_name = (EditText)findViewById(R.id.editText3);
        ET_user_name = (EditText)findViewById(R.id.editText4);
        ET_user_pass = (EditText)findViewById(R.id.editText5);
    }

    public void user_reg(View view)
    {
        name = ET_name.getText().toString();
        user_name = ET_user_name.getText().toString();
        user_pass = ET_user_pass.getText().toString();
        String method = "register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,name,user_name,user_pass);
        finish();
    }
}
