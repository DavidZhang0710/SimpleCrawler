package com.david;

import edu.uci.ics.crawler4j.crawler.CrawlController;

public class Main {
    private static final String ROOT_URL = "https://www.foxnews.com";
    private static final String SITE_NAME = "FoxNews";
    public static void main(String[] args) throws Exception {
        String path = "./data/crawler/";
        SimpleController simpleController = new SimpleController(25, path, 20_000, 16);
//        SimpleController simpleController = new SimpleController(10, path, 50, 16);
        simpleController.addSeed(ROOT_URL);

        SimpleCrawlerStats stats = new SimpleCrawlerStats();
        CrawlController.WebCrawlerFactory<SimpleCrawler> factory = () -> new SimpleCrawler(stats);
        simpleController.start(factory);

        CsvUtils.saveFetchList(stats.getFetchList(), "./data/output/fetch_" + SITE_NAME + ".csv");
        CsvUtils.saveVisitList(stats.getVisitList(), "./data/output/visit_" + SITE_NAME + ".csv");
        CsvUtils.saveUrlList(stats.getUrlList(), "./data/output/urls_" + SITE_NAME + ".csv");
    }
}
