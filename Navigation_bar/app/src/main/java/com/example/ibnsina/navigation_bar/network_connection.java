package com.example.ibnsina.navigation_bar;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class network_connection extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Bitmap bitmap = null;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_connection);

        b1 = (Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetConection();
                downloadimage("http://www.tutorialspoint.com/green/images/logo.png");
            }
        });


    }
    private void downloadimage(String urlStr)
    {
        progressDialog = ProgressDialog.show(this,"","Downloading Image From URL"+urlStr);
        final String url = urlStr;
        new  Thread(){
            public void run(){
                InputStream in =null;
                Message msg = Message.obtain();
                msg.what = 1;

                try{
                    in = openHttpConnection(url);
                    bitmap = BitmapFactory.decodeStream(in);
                    Bundle b = new Bundle();
                    b.putParcelable("bitmap",bitmap);
                    msg.setData(b);
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message.sendMessage(msg);

            }
        }.start();
    }

    private InputStream openHttpConnection(String urStr){

        InputStream in = null;
        int resCode = -1;

        try
        {
            URL url = new URL(urStr);
            URLConnection urlconn = url.openConnection();

            if(!(urlconn instanceof HttpURLConnection)){
                throw new IOException("URL is not Http URL");
            }

            HttpURLConnection httpconn = (HttpURLConnection)urlconn;
            httpconn.setAllowUserInteraction(false);
            httpconn.setInstanceFollowRedirects(true);
            httpconn.connect();
            resCode = httpconn.getResponseCode();

            if(resCode == HttpURLConnection.HTTP_OK)
            {
                in = httpconn.getInputStream();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    private Handler message = new Handler(){
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            ImageView imageview = (ImageView)findViewById(R.id.imageView);
            imageview.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
            progressDialog.dismiss();
        }
    };

    private boolean checkInternetConection(){
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        //check for network connections.
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED){

            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED){
            Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }


}
