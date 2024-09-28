package com.david;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

import java.util.HashSet;
import java.util.Set;

public class SimpleCrawler extends WebCrawler {

    private final static Set<String> visitedUrls = new HashSet<>();

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        if (visitedUrls.contains(url)) {
            return;
        }
        visitedUrls.add(url);
        System.out.println("Visited: " + url);
    }
}
