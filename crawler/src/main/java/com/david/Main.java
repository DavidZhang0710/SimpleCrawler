package com.david;

public class Main {
    private final String ROOT_URL = "https://www.foxnews.com";
    public static void main(String[] args) throws Exception {
        int crawlerNum = 0;
        String path = "./data/crawler/";
//        int maxPages = 20_000;
//        int maxDepth = 16;
        int maxPages = 100;
        int maxDepth = 4;
        SimpleController simpleController = new SimpleController(crawlerNum, path, maxPages, maxDepth);
        simpleController.start();
    }
}
