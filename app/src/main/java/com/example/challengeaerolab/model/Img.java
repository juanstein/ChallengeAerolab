package com.example.challengeaerolab.model;

/**
 * Created by Juan on 06/12/2017.
 */

public class Img {


    private String url;
    private String hdUrl;

    public Img() {
    }

    public Img(String url, String hdUrl) {
        this.url = url;
        this.hdUrl = hdUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }
}
