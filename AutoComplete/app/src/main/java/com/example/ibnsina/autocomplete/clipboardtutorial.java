package com.example.ibnsina.autocomplete;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class clipboardtutorial extends AppCompatActivity {

    Button btn1,btn2;
    EditText txt1,txt2;

    private ClipboardManager clipboardManager;
    private ClipData clipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboardtutorial);

        btn1 = (Button)findViewById(R.id.button5);
        btn2 = (Button)findViewById(R.id.button6);

        txt1 =(EditText)findViewById(R.id.editText);
        txt2 = (EditText)findViewById(R.id.editText2);

        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txt1.getText().toString();

                clipData = ClipData.newPlainText("text",text);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(clipboardtutorial.this, "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData data = clipboardManager.getPrimaryClip();
                ClipData.Item item = data.getItemAt(0);

                String text = item.getText().toString();
                txt2.setText(text);
                Toast.makeText(clipboardtutorial.this, "Text Pasted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
