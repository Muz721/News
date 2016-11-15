package com.example.administrator.eneity;

/**
 * Created by Administrator on 2016/11/10.
 */

public class LogInfo {
    String time;
    String address;
    int device;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public LogInfo(int device, String time, String address) {
        this.device = device;
        this.time = time;
        this.address = address;
    }

    @Override
    public String toString() {
        return "LogInfo{" +
                "uid='" + time + '\'' +
                ", address='" + address + '\'' +
                ", device=" + device +
                '}';
    }
}
