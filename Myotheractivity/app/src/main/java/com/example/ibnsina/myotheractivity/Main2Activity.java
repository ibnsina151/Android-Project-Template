package com.example.ibnsina.myotheractivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.health.SystemHealthManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    NotificationCompat.Builder notificationCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        notificationCompat = new NotificationCompat.Builder(this);
    }



    public void onClick(View view)
    {
        notificationCompat.setSmallIcon(R.drawable.cow);
        notificationCompat.setContentTitle("Programming School");
        notificationCompat.setContentText("Hello Viewers, Wellcome to our Tutorial");
        notificationCompat.setWhen(System.currentTimeMillis());


        Intent resultintent = new Intent(this,MainActivity.class);

        TaskStackBuilder taskStackBuilder =  TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(resultintent);
        PendingIntent resultpendingintent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationCompat.setContentIntent(resultpendingintent);


        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1245, notificationCompat.build());
    }
}
