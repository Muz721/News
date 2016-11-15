package com.example.administrator.eneity;

/**
 * Created by Administrator on 2016/11/14.
 */

public class CommentCommentInfo {
    String cid;
    String uid;
    String portrait;
    String stamp;
    String content;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentCommentInfo(String cid, String uid, String portrait, String stamp, String content) {
        this.cid = cid;
        this.uid = uid;
        this.portrait = portrait;
        this.stamp = stamp;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentCommentInfo{" +
                "cid='" + cid + '\'' +
                ", uid='" + uid + '\'' +
                ", portrait='" + portrait + '\'' +
                ", stamp='" + stamp + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
