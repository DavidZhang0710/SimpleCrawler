package com.david;

import edu.uci.ics.crawler4j.crawler.CrawlController;

public class Main {
    private static final String ROOT_URL = "https://www.foxnews.com";
    public static void main(String[] args) throws Exception {
        String path = "./data/crawler/";
//        SimpleController simpleController = new SimpleController(1, path, 20_000, 16);
        SimpleController simpleController = new SimpleController(1, path, 100, 4);
        simpleController.addSeed(ROOT_URL);

        SimpleCrawlerStats stats = new SimpleCrawlerStats();
        CrawlController.WebCrawlerFactory<SimpleCrawler> factory = () -> new SimpleCrawler(stats);
        simpleController.start(factory);
    }
}
