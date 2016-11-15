package com.example.administrator.eneity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/14.
 */

public class CommentInfo {
    String message;
    String status;
    ArrayList<CommentCommentInfo> data;

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

    public ArrayList<CommentCommentInfo> getData() {
        return data;
    }

    public void setData(ArrayList<CommentCommentInfo> data) {
        this.data = data;
    }

    public CommentInfo(String message, String status, ArrayList<CommentCommentInfo> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
