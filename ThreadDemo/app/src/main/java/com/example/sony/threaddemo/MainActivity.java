package com.example.sony.threaddemo;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sony.threaddemo.NetWorkManager;
import com.example.sony.threaddemo.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    public static String link = "https://fb.com/nguyenduc0496";
    ImageView imv;
    Button button;
    TextView tv;
    String url;
    int time = 0;
    public static String TAG = MainActivity.class.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetWorkManager.init(this);
        imv = (ImageView) findViewById(R.id.imv);
        button = (Button) findViewById(R.id.btnclick);
        tv = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetWorkManager.getInstance().isConnectedToInternet()) {
                    try {
                        String url = new LoadImage().execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        tv.setText(String.valueOf(time++));
                    }
                });
            }
        }, 0, 50);







    }

    class LoadImage extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Document document = Jsoup.connect(link).get();
                Log.d(TAG, document.toString());
                Element element = document.getElementsByClass("profilePic img").first();
                url = element.attr("src");
                Log.d(TAG, String.valueOf(url));



            } catch (IOException e) {
                e.printStackTrace();
            }

            return url;
        }

        public LoadImage() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            Picasso.with(getBaseContext()).load(url).into(imv);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String string) {
            super.onCancelled(string);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }



}
