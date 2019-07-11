package com.ming.networkreceiver;

/**
 * 网络连接状态变化事件
 */
public class NetworkChangeEvent {
    //无连接
    public static final int TYPE_NONE = -1;
    //移动数据
    public static final int TYPE_MOBILE = 0;
    //WIFI
    public static final int TYPE_WIFI = 1;
    //网络连接类型
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
