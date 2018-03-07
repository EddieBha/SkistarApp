package com.example.eddieage.skistarapp.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eddieage.skistarapp.R;

/**
 * Created by eddieage on 2018-02-09.
 */

public class ConnectionActivity extends AppCompatActivity {
    static Context context;
    /**
     * We use this class to determine if the application has been connected to either WIFI Or Mobile
     * Network, before we make any network request to the server.
     * <p>
     * The class uses two permission - INTERNET and ACCESS NETWORK STATE, to determine the user's
     * connection stats
     */

    private static ConnectionActivity instance = new ConnectionActivity();
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check for Internet Connection first
                if (ConnectionActivity.getInstance(getApplicationContext()).isOnline()) {
                    /**
                     * Internet is available, Toast It!
                     */
                    Toast.makeText(getApplicationContext(), "WiFi/Mobile Networks Connected!", Toast.LENGTH_SHORT).show();
                } else {
                    /**
                     * Internet is NOT available, Toast It!
                     */
                    Toast.makeText(getApplicationContext(), "Ooops! No WiFi/Mobile Networks Connected!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static ConnectionActivity getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
}

