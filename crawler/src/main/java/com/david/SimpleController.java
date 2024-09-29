package com.david;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class SimpleController {
    int crawlerNum;
    CrawlConfig config;
    CrawlController controller;
    SimpleController(int num, String path, int maxPages, int maxDepth) throws Exception {
        crawlerNum = num;
        config = new CrawlConfig();
        config.setCrawlStorageFolder(path);
        config.setMaxPagesToFetch(maxPages);
        config.setMaxDepthOfCrawling(maxDepth);
        config.setIncludeBinaryContentInCrawling(true);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer= new RobotstxtServer(robotstxtConfig, pageFetcher);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);
    }
    public void addSeed(String seed) {
        controller.addSeed(seed);
    }

    public void start(CrawlController.WebCrawlerFactory<?> factory) {
        controller.start(factory, crawlerNum);
    }
}
