package com.example.administrator.eneity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MainInfo {
    String message;
    String status;
    ArrayList<NewsInfo> data;

    public MainInfo(String message, String status, ArrayList<NewsInfo> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<NewsInfo> getData() {
        return data;
    }

    public void setData(ArrayList<NewsInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MainInfo{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
