package com.finder.stotomas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button btnFind, btnFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar= findViewById(R.id.pgsBar);
        btnFind= findViewById(R.id.btnFind);
        btnFinder= findViewById(R.id.btnFinder);


        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFind.setEnabled(false);
                new Task1().execute();
            }
        });

        btnFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFinder.setEnabled(false);
                new Task2().execute();
            }
        });
    }



    private class Task1 extends AsyncTask<Void, Void, Void>{

        int progress;
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progress= 0;
            btnFind.setEnabled(false);
            btnFinder.setClickable(false);

        }

        @Override
        protected Void doInBackground(Void... params){
            while (progress<100) {
                progress += 5;
                SystemClock.sleep(200);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            progressBar.setVisibility(View.INVISIBLE);
            btnFind.setEnabled(true);
            btnFinder.setClickable(true);
            Intent intent = new Intent(MainActivity.this, FindActivity.class);
            startActivity(intent);
        }

    }

    private class Task2 extends AsyncTask<Void, Void, Void>{

        int progress;
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progress= 0;
            btnFinder.setEnabled(false);
            btnFind.setClickable(false);


        }

        @Override
        protected Void doInBackground(Void... params){

            while (progress<100) {
                progress += 5;
                SystemClock.sleep(500);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            progressBar.setVisibility(View.INVISIBLE);
            btnFinder.setEnabled(true);
            btnFind.setClickable(true);
            Intent intent = new Intent(MainActivity.this, FinderActivity.class);
            startActivity(intent);
        }

    }


}