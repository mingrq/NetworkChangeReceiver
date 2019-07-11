package com.ming.networkreceiver;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

/**
 * 网络变化广播接收者管理类
 */
public class NetWorkChange {

    private static NetworkChangeReceiver receiver;


    /**
     * 注册网络变化监听
     *
     * @param application
     */
    public static synchronized void register(Application application) {
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        application.registerReceiver(receiver, filter);
    }

    /**
     * 注销网络变化监听
     */
    public static synchronized void unregister(Application application) {
        if (receiver != null) {
            application.unregisterReceiver(receiver);
        }
    }
}
