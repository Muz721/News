package com.example.administrator.eneity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/11.
 */

public class LoginLogInfo {
    String uid;
    String integration;
    ArrayList<LogInfo> log;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public ArrayList<LogInfo> getLog() {
        return log;
    }

    public void setLog(ArrayList<LogInfo> log) {
        this.log = log;
    }

    public LoginLogInfo(String uid, String integration, ArrayList<LogInfo> log) {
        this.uid = uid;
        this.integration = integration;
        this.log = log;
    }

    @Override
    public String toString() {
        return "LoginLogInfo{" +
                "uid='" + uid + '\'' +
                ", integration='" + integration + '\'' +
                ", log=" + log +
                '}';
    }
}
