package com.david;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.HashSet;
import java.util.Set;

public class SimpleCrawler extends WebCrawler {

    private final static Set<String> visitedUrls = new HashSet<>();

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        // 过滤重复URL
        if (visitedUrls.contains(url)) {
            return;
        }
        visitedUrls.add(url);

        // 打印网页标题
        System.out.println("Visited: " + url);
    }

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "./data/crawl/root"; // 爬虫存储目录
        int numberOfCrawlers = 1; // 爬虫数量

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(2); // 最大抓取深度
        config.setMaxPagesToFetch(100); // 最大抓取页面数

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer= new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        controller.addSeed("https://example.com"); // 添加种子URL

        controller.start(SimpleCrawler.class, numberOfCrawlers);
    }
}
