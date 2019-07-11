package com.ming.networkreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.greenrobot.eventbus.EventBus;


import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * 网络改变广播接收者
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int connectType;
        //获取网络连接
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            connectType = networkInfo.getType();
        } else {
            connectType = -1;
        }
        //添加事件
        NetworkChangeEvent event = new NetworkChangeEvent();
        event.setType(connectType);
        //发送事件
        EventBus.getDefault().post(event);
    }
}
