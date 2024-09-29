package com.david;

public class FetchData {
    String url;
    Integer code;

    public FetchData(String url, Integer code) {
        this.url = url;
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
