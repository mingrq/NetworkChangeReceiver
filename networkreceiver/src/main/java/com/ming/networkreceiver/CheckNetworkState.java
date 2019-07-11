package com.ming.networkreceiver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;


/**
 * 检查网络状态
 */
public class CheckNetworkState {
    private Context context;

    public CheckNetworkState(Context context) {
        this.context = context;
    }

    /**
     * 检查网络连接方式
     * WIFI  OR  数据流量
     */
    public int checkConnectionMethod() {
        int connectType;
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            connectType = networkInfo.getType();
        } else {
            connectType = -1;
        }
        return connectType;
    }

    /**
     * 检测当前网络是否能连接到互联网
     * @return
     */
    public boolean isNetworkOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 3 www.baidu.com");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *检测当前网络是否能连接到服务器
     * @param location 服务器地址 域名 或 IP
     * @return true是可以上网，false是不能上网
     */
    public boolean isServerOnline(String location) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1 "+location);
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
