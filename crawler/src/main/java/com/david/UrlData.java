package com.david;

public class UrlData {
    String url;
    String resident;

    public UrlData(String url, String resident) {
        this.url = url;
        this.resident = resident;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResident() {
        return resident;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }
}
