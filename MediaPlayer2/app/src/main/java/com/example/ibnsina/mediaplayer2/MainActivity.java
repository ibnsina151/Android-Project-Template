package com.example.ibnsina.mediaplayer2;


import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button b1,b2,b3,b4;
    private TextView tx1,tx2,tx3;

    private double startTime = 0;
    private double finalTime = 0;

    private MediaPlayer mediaPlayer;

    private Handler myHandler = new Handler();
    private SeekBar seekBar;
    private int forwardTime = 5000;
    private  int backwardTime = 5000;

    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);

        tx1 = (TextView) findViewById(R.id.textView);
        tx2 = (TextView) findViewById(R.id.textView2);
        tx3 = (TextView) findViewById(R.id.textView3);
        tx2.setText("Song.mp3");

        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        b2.setEnabled(false);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing Sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if(oneTimeOnly ==0)
                {
                    seekBar.setMax((int)finalTime);
                    oneTimeOnly = 1;
                }

                tx3.setText(String.format("&d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long)finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long)finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finalTime)))
                );

                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                seekBar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing sound",
                        Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;
                if(temp-forwardTime <=finalTime)
                {
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int)startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped forward 5 second",
                            Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Cannot jump forward 5 second", Toast.LENGTH_SHORT).show();
                }
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if(temp -backwardTime >0)
                {
                    startTime = startTime -backwardTime;
                    mediaPlayer.seekTo((int)startTime);
                    Toast.makeText(getApplicationContext(), "You have Jumped backward 5 second", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Cannot jump backward 5 second", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long)startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long)startTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)startTime)))
            );
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this,100);
        }
    };
}
