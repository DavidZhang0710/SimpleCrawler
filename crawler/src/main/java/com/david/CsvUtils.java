package com.david;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvUtils {
    public static void createDirectories(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }
    // 保存 fetchList 到 CSV
    public static void saveFetchList(List<FetchData> fetchList, String filePath) throws IOException {
        createDirectories(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入 CSV 头部
            writer.write("URL,Status\n");

            // 写入每一行数据
            for (FetchData data : fetchList) {
                writer.write(data.getUrl() + "," + data.getCode() + "\n");
            }
        }
    }

    // 保存 visitList 到 CSV
    public static void saveVisitList(List<VisitData> visitList, String filePath) throws IOException {
        createDirectories(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入 CSV 头部
            writer.write("URL,FileSize(Bytes),Outlinks,ContentType\n");

            // 写入每一行数据
            for (VisitData data : visitList) {
                writer.write(data.getUrl() + "," + data.getFileSize() + "," + data.getOutlinkNum() + "," + data.getContentType() + "\n");
            }
        }
    }

    // 保存 urlList 到 CSV
    public static void saveUrlList(List<UrlData> urlList, String filePath) throws IOException {
        createDirectories(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入 CSV 头部
            writer.write("URL,Indicator\n");

            // 写入每一行数据
            for (UrlData data : urlList) {
                writer.write(data.getUrl() + "," + data.getResident() + "\n");
            }
        }
    }
}
