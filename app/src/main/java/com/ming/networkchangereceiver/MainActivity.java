package com.ming.networkchangereceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ming.networkreceiver.CheckNetworkState;
import com.ming.networkreceiver.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkChangeReceiver receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_fdfg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckNetworkState checkNetworkState = new CheckNetworkState(getBaseContext());
                checkNetworkState.checkConnectionMethod();
            }
        });
        TextView textView1 = findViewById(R.id.tv_fdwg);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckNetworkState checkNetworkState = new CheckNetworkState(getBaseContext());
                checkNetworkState.checkConnectionActive();
            }
        });
    }
}
