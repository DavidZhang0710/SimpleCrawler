package com.david;

public class VisitData {
    String url;
    Long fileSize;
    Integer outlinkNum;
    String contentType;

    public VisitData(String url, Long fileSize, Integer outlinkNum, String contentType) {
        this.url = url;
        this.fileSize = fileSize;
        this.outlinkNum = outlinkNum;
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getOutlinkNum() {
        return outlinkNum;
    }

    public void setOutlinkNum(Integer outlinkNum) {
        this.outlinkNum = outlinkNum;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
