package com.example.kbp2;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.shockwave.pdfium.PdfDocument;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PdfViewerActivity extends AppCompatActivity {
    private String url;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdfView);


        url = getIntent().getStringExtra("pdfUrl");
        new pdfDownload().execute(url);
    }


    private class pdfDownload extends AsyncTask<String, Void, InputStream>{


        @Override
        protected InputStream doInBackground(String... strings) {

            InputStream inputStream = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }


        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
            pdfView.getCurrentPage();
            pdfView.getPageCount();
        }
    }

}