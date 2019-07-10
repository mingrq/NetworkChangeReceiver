package com.ming.networkreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络改变广播接收者
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
       /* if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //api 23 以下
            lollipopDown();
        } else {
            //api 23 以上
            lollipopUp();
        }*/
        lollipopDown();
    }

    /**
     * api 23 以上使用
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void lollipopUp() {
        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取所有网络连接的信息
        Network[] networks = connMgr.getAllNetworks();
        //用于存放网络连接信息
        StringBuilder sb = new StringBuilder();
        //通过循环将网络信息逐个取出来
        List<String> dsf = new ArrayList<>();
        for (int i = 0; i < networks.length; i++) {
            //获取ConnectivityManager对象对应的NetworkInfo对象
            NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
            sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            dsf.add(networkInfo.getTypeName());
        }
        Toast.makeText(context, dsf.toString()+"    "+ networks.length, Toast.LENGTH_SHORT).show();
    }

    /**
     * api 23 以下使用
     */
    private void lollipopDown() {
        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取ConnectivityManager对象对应的NetworkInfo对象
        // 获取WIFI连接的信息
        NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //获取移动数据连接的信息
        NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
            Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
        } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
            Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
        } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
            Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 在子线程里开启该方法，可检测当前网络是否能打开网页
     * true是可以上网，false是不能上网
     *
     */
    public boolean isNetworkOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 3 www.baidu.com");
            int exitValue = ipProcess.waitFor();
            Log.i("Avalible", "Process:"+exitValue);
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
