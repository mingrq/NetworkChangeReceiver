# 网络连接监测

最新版本1.0.0
#
### 使用
#
```
在 build.gradle 中添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.mingrq:NetworkChangeReceiver:Tag'
	}
```
### 类
#
### CheckNetworkState
    检查网络连接方式
      int checkConnectionMethod()
        返回 -1：无网络连接 0：数据流量 1：WIFI
      
    检测当前网络是否能连接到互联网
      boolean isNetworkOnline()
        返回 true：可以连接到网络 false：无法连接到网络
      
    检测当前网络是否能连接到服务器
      boolean isServerOnline(String location)
        location：服务器IP 或 域名
        返回：true：可以连接到网络 false：无法连接到网络
#    
### 监听网络连接状态变化广播
#
```
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //注册网络状态监听广播
        NetWorkChange.register(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //注销网络状态监听广播
       NetWorkChange.unregister(this);
    }
}
```
#### 使用
#
  程序已使用EventBus 3.1.1发送事件
  
  以下为第三方使用方法
  ```
  public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //注册
        EventBus.getDefault().register(this);
        
        
        setContentView(R.layout.activity_main);

    }



    /**
     * 网络状态监听事件处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void NetworkChangeEventDispose(NetworkChangeEvent event) {
        Toast.makeText(getBaseContext(),"test::"+event.getType(),Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        
        
        //注销
        EventBus.getDefault().unregister(this);
        
        
    }
}
```
  
