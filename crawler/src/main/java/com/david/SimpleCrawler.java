package com.david;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class SimpleCrawler extends WebCrawler {

    private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|mp3|mp4|zip|gz))$");
    private static final Pattern PATTERNS = Pattern.compile(".*(\\.(html?|gif|png|jpg|jpeg|pdf|doc))$");
    private SimpleCrawlerStats stats;
    SimpleCrawler(SimpleCrawlerStats stats) {
        this.stats = stats;
    }

    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        String url = webUrl.getURL();
        stats.getFetchList().add(new FetchData(url, statusCode));
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        String domain = page.getWebURL().getDomain();
        String outlinkUrl = url.getURL();
        String outlinkDomain = url.getDomain();
        String indicator = outlinkDomain.equals(domain) ? "OK" : "N_OK";
        stats.getUrlList().add(new UrlData(outlinkUrl, indicator));

        String urlString = url.getURL().toLowerCase();
        if (EXCLUSIONS.matcher(urlString).matches()) {
            return false;
        }

        if (PATTERNS.matcher(urlString).matches()) {
            return true;
        }
        return true;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        long fileSize = page.getContentData().length;
        int outlinksCount = 0;
        String contentType = page.getContentType();
        String mimeType = contentType.split(";")[0].trim();
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            outlinksCount = htmlParseData.getOutgoingUrls().size();
        }

        stats.getVisitList().add(new VisitData(url, fileSize, outlinksCount, mimeType));
    }
}
