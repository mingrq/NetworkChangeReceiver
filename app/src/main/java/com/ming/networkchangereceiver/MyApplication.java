package com.ming.networkchangereceiver;

import android.app.Application;

import com.ming.networkreceiver.NetWorkChange;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkChange.register(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
       NetWorkChange.unregister(this);
    }
}
