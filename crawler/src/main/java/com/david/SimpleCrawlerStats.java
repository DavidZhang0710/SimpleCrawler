package com.david;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleCrawlerStats {
    List<FetchData> fetchList;
    List<VisitData> visitList;
    List<UrlData> urlList;
    int cnt;

    SimpleCrawlerStats() {
        fetchList = new ArrayList<>();
        visitList = new ArrayList<>();
        urlList = new ArrayList<>();
        cnt = 0;
    }

    public List<FetchData> getFetchList() {
        return fetchList;
    }
    public List<VisitData> getVisitList() {
        return visitList;
    }
    public List<UrlData> getUrlList() {
        return urlList;
    }
    public int getCnt() {
        return cnt;
    }
    public void inc() {
        cnt++;
    }
}
