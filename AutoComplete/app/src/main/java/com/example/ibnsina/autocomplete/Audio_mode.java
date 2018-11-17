package com.example.ibnsina.autocomplete;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Audio_mode extends AppCompatActivity {


    Button mode,silent,vibrate,ring;
    private AudioManager myaudioManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_mode);

        myaudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        mode =(Button)findViewById(R.id.button);
        silent =(Button)findViewById(R.id.button2);
        vibrate =(Button)findViewById(R.id.button3);
        ring  =(Button)findViewById(R.id.button4);

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myaudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(Audio_mode.this,"Now in Vibrate Mode",Toast.LENGTH_LONG).show();
            }
        });

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myaudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(Audio_mode.this, "Now in Silent mode", Toast.LENGTH_SHORT).show();
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myaudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(Audio_mode.this, "Now in Ring mode", Toast.LENGTH_SHORT).show();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mod = myaudioManager.getRingerMode();
                if(mod == AudioManager.RINGER_MODE_VIBRATE)
                {
                    Toast.makeText(Audio_mode.this, "Now In Vibrate Mode", Toast.LENGTH_SHORT).show();
                }
                else if (mod==AudioManager.RINGER_MODE_SILENT){
                    Toast.makeText(Audio_mode.this, "Now In Silent Mode", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Audio_mode.this, "Now in Ringing Mode", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
