package com.david;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.HashSet;
import java.util.Set;

public class SimpleCrawler extends WebCrawler {

    private final static Set<String> visitedUrls = new HashSet<>();
    private SimpleCrawlerStats stats;
    SimpleCrawler(SimpleCrawlerStats stats) {
        this.stats = stats;
    }

    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        String url = webUrl.getURL();
        // 保存到 fetchList 中
        stats.getFetchList().add(new FetchData(url, statusCode));
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        String domain = page.getWebURL().getDomain();
        String outlinkUrl = url.getURL();
        String outlinkDomain = url.getDomain();

        // 判断是否为内部链接
        String indicator = outlinkDomain.equals(domain) ? "OK" : "N_OK";

        // 将数据存入 urlList
        stats.getUrlList().add(new UrlData(outlinkUrl, indicator));
        return true;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        // 获取文件大小
        long fileSize = page.getContentData().length;

        // 如果页面是 HTML 类型，获取 outlinks 数和 content-type
        int outlinksCount = 0;
        String contentType = page.getContentType();
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            outlinksCount = htmlParseData.getOutgoingUrls().size();
        }

        // 将数据存入 visitList
        stats.getVisitList().add(new VisitData(url, fileSize, outlinksCount, contentType));
    }
}
