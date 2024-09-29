package com.david;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleCrawlerStats {
    final static String SITE_NAME = "FoxNews";
    List<FetchData> fetchMap;
    List<VisitData> visitList;
    List<UrlData> urlList;

    SimpleCrawlerStats() {
        fetchMap = new ArrayList<>();
        visitList = new ArrayList<>();
        urlList = new ArrayList<>();
    }

    public void generateFiles() {

    }
}
