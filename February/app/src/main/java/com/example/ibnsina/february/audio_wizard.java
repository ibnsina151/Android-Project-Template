package com.example.ibnsina.february;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;

public class audio_wizard extends AppCompatActivity  {

    ImageButton btn_pre,btn_pause,btn_play,btn_next;
    private MediaPlayer mediaPlayer;
    private int Counter;
    SeekBar seek_bar;

    AudioManager audiomanager;

    final String songs_urls[] = {"http://79.170.40.235/munim007.com/21February_Content/music/AmarSonar",
            "http://79.170.40.235/munim007.com/21February_Content/music/app_1.mp3"};// your URL here

    final String song_titels[] = {"আমার ভাইয়ের রক্তে রাঙানো","আমার ভাইয়ের রক্তে রাঙানো (যান্ত্রিক)","আমি বাংলার গান খাই",
            "আমি দাম দিয়ে কিনেছি বাংলা ","ভাষায় আন্দোলন ১৯৫২","ওরা আমার মুখের ভাষায় কাইরা নিতে চায়",
            "আমি দাম দিয়ে কিনেছি বাংলা ","ভাষায় আন্দোলন ১৯৫২","ওরা আমার মুখের ভাষায় কাইরা নিতে চায়",
            "আমি দাম দিয়ে কিনেছি বাংলা ","ভাষায় আন্দোলন ১৯৫২","ওরা আমার মুখের ভাষায় কাইরা নিতে চায়"};
    private TextView songs_title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_wizard);

        btn_pre = (ImageButton)findViewById(R.id.P_prevous);
        btn_pause = (ImageButton)findViewById(R.id.P_pushe);
        btn_play = (ImageButton)findViewById(R.id.P_play);
        btn_next = (ImageButton)findViewById(R.id.P_next);
        seek_bar = (SeekBar)findViewById(R.id.volume);
        audiomanager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int vol_max = audiomanager.getStreamMaxVolume(audiomanager.STREAM_MUSIC);
        int vol_current = audiomanager.getStreamVolume(audiomanager.STREAM_MUSIC);
        seek_bar.setMax(vol_max);
        seek_bar.setProgress(vol_current);


        songs_title = (TextView)findViewById(R.id.song_title);


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        Counter = 0;



        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Counter > 0){
                    Counter = Counter -1;

                    try{mediaPlayer.reset();}
                    catch(Exception e){}
                    try {
                        mediaPlayer.setDataSource(songs_urls[Counter]);
                    }catch(IllegalArgumentException e) {
                        e.printStackTrace();
                    }catch(SecurityException e){
                        e.printStackTrace();
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                    songs_title.setText(song_titels[Counter].toString());
                }
                else
                {
                    Toast.makeText(audio_wizard.this, "First Song", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager cn;



                if (Counter < songs_urls.length){
                        Counter = Counter +1;
                    try{mediaPlayer.reset();}
                    catch(Exception e){}

                    try {
                        mediaPlayer.setDataSource(songs_urls[Counter]);
                    }catch(IllegalArgumentException e) {
                        e.printStackTrace();
                    }catch(SecurityException e){
                        e.printStackTrace();
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                    songs_title.setText(song_titels[Counter].toString());
                    }
                    else
                    {
                        Toast.makeText(audio_wizard.this, "Last_Song", Toast.LENGTH_SHORT).show();
                    }
            }
        });




        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mediaPlayer.setDataSource(songs_urls[0]);
                }catch(IllegalArgumentException e) {
                    e.printStackTrace();
                }catch(SecurityException e){
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                }catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();

                songs_title.setText(song_titels[0].toString());
            }
        });



        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        // LIst item.
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, song_titels);
        //ArrayAdapter activity_listview = new ArrayAdapter<String>(this,R.layout.activity_audio_wizard,song_titels)
        ListView listView = (ListView) findViewById(R.id.playlist);
        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);}
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
