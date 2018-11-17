package com.example.ibnsina.mysqldbdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText ET_name,ET_pass;
    String login_name,Login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_name = (EditText)findViewById(R.id.editText);
        ET_pass = (EditText)findViewById(R.id.editText2);
    }

    public void userLogin(View view)
    {
        login_name = ET_name.getText().toString();
        Login_pass = ET_pass.getText().toString();
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,login_name,Login_pass);
    }

    public void userReg(View view)
    {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
}
