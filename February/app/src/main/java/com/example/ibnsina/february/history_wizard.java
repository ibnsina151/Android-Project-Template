package com.example.ibnsina.february;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class history_wizard extends AppCompatActivity {


    PDFView pdfView;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_wizard);

        pdfView = (PDFView)findViewById(R.id.pdf_viewer);
        bar = (ProgressBar)findViewById(R.id.progressBar);

        new  RetrievePDFStream().execute("http://79.170.40.235/munim007.com/21February_Content/pdf_file/21_history.pdf");


    }

    public void back_main(View view)
    {
        this.finish();
    }

    class RetrievePDFStream extends AsyncTask<String,Void,byte[]> {

        @Override
        protected byte[] doInBackground(String... params) {

            InputStream inputStream = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                return null;
            }
            try {
                return IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            pdfView.fromBytes(bytes).load();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


}
