package com.david;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

public class SimpleCrawler extends WebCrawler {

    private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|mp3|mp4|zip|gz|json|ttf))$");
    private static final Pattern PATTERNS = Pattern.compile(".*(\\.(html?|pdf|doc|docx|apng|png|avif|gif|jpg|jpeg|jfif|pjpeg|pjp|svg|webp))$");
    private static final Pattern DEFAULT_PATTERNS = Pattern.compile(".*/[^.]*$");
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
        String outlinkUrl = url.getURL();
        String outlinkDomain = url.getDomain();
        String outlinkSubDomain = url.getSubDomain();
        if (outlinkDomain.equals("foxnews.com") && ((outlinkSubDomain.equals("www") || outlinkSubDomain.isEmpty()))) {
            String indicator = "OK";
            stats.getUrlList().add(new UrlData(outlinkUrl, indicator));
        }
        else {
            String indicator = "N_OK";
            stats.getUrlList().add(new UrlData(outlinkUrl, indicator));
            return false;
        }
        String urlString = url.getURL().toLowerCase();
        if (EXCLUSIONS.matcher(urlString).matches()) {
            return false;
        }
        return PATTERNS.matcher(urlString).matches() || DEFAULT_PATTERNS.matcher(urlString).matches();
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
        stats.inc();
        System.err.println(stats.getCnt());
    }
}
