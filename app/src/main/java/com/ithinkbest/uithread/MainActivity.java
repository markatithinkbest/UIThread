package com.ithinkbest.uithread;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Random;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textView);
        queue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                textView.setText("99");
                //   randomize();

//                String url ="http://www.google.com";
//                String url ="http://twibm003.herokuapp.com/dummy"; // WHY NOT WORKING FOR THIS CASE?
//              String url ="http://twibm003.herokuapp.com/dummy"; // WHY NOT WORKING FOR THIS CASE?
//                http://twibm003.herokuapp.com/dummy

//                String url =" http://stackoverflow.com/questions/20059576/import-android-volley-to-android-studio";

                String url ="http://expressjs.com/en/api.html";
                Log.d("mark123", "url is " + url);

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.

                                textView.setText("Response is: "+ response.substring(0,50));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
    }

    Handler handler = new Handler();

    public void randomize(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                generateNumber();
            }
        };
        new Thread(runnable).start();
    }

    Random rnd = new Random();
    int x;

    private void generateNumber() {
        for (int i = 0; i <= 40; i++) {
            x = rnd.nextInt(100);


            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(""+x);
                }
            });
        }
    }


}
