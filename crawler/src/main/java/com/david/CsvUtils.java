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
    public static void saveFetchList(List<FetchData> fetchList, String filePath) throws IOException {
        createDirectories(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("URL,Status\n");
            for (FetchData data : fetchList) {
                writer.write("\"" + data.getUrl() + "\"" + "," + data.getCode() + "\n");
            }
        }
    }

    public static void saveVisitList(List<VisitData> visitList, String filePath) throws IOException {
        createDirectories(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("URL,FileSize(Bytes),Outlinks,ContentType\n");
            for (VisitData data : visitList) {
                writer.write("\"" + data.getUrl() + "\"," + data.getFileSize() + "," + data.getOutlinkNum() + "," + data.getContentType() + "\n");
            }
        }
    }

    public static void saveUrlList(List<UrlData> urlList, String filePath) throws IOException {
        createDirectories(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("URL,Indicator\n");
            for (UrlData data : urlList) {
                writer.write("\"" + data.getUrl() + "\"," + data.getResident() + "\n");
            }
        }
    }
}
