package com.david;

import java.util.ArrayList;
import java.util.List;

public class SimpleCrawlerStats {
    List<FetchData> fetchList;
    List<VisitData> visitList;
    List<UrlData> urlList;

    SimpleCrawlerStats() {
        fetchList = new ArrayList<>();
        visitList = new ArrayList<>();
        urlList = new ArrayList<>();
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
}
