package com.example.administrator.eneity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RegisterMainInfo {
    String message;
    String status;
    ArrayList<RegisterInfo> data;

    public RegisterMainInfo(String message, String status, ArrayList<RegisterInfo> data) {
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

    public ArrayList<RegisterInfo> getData() {
        return data;
    }

    public void setData(ArrayList<RegisterInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegisterMainInfo{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
