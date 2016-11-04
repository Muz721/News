package com.example.administrator.eneity;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RegisterInfo {
String result;
    String token;
    String explain;

    public RegisterInfo(String result, String token, String explain) {
        this.result = result;
        this.token = token;
        this.explain = explain;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "result='" + result + '\'' +
                ", token='" + token + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }
}
