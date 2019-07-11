package com.ming.networkchangereceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ming.networkreceiver.CheckNetworkState;
import com.ming.networkreceiver.NetworkChangeEvent;
import com.ming.networkreceiver.NetworkChangeReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_fdfg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckNetworkState checkNetworkState = new CheckNetworkState(getBaseContext());
                Toast.makeText(getBaseContext(),String.valueOf(checkNetworkState.checkConnectionMethod()),Toast.LENGTH_LONG).show();
            }
        });
        TextView textView1 = findViewById(R.id.tv_fdwg);
        textView1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                CheckNetworkState checkNetworkState = new CheckNetworkState(getBaseContext());
                Toast.makeText(getBaseContext(),String.valueOf(checkNetworkState.checkConnectionMethod()),Toast.LENGTH_LONG).show();
                checkNetworkState.isNetworkOnline();
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(NetworkChangeEvent event) {
        Toast.makeText(getBaseContext(),"test::"+event.getType(),Toast.LENGTH_LONG).show();
    }
}
