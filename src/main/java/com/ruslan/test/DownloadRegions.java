package com.ruslan.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class DownloadRegions implements Callable<ConcurrentLinkedQueue<String>> {

    public ConcurrentLinkedQueue<String> downloadInfoLinkToFile() { // делаем запрос по районам и сохраняем в файл
        System.out.println("Started... " + LocalDateTime.now());
        System.out.println("Current thread: " + Thread.currentThread().getName());
        ConcurrentLinkedQueue<String> concurrentLinkedQueueLink = new ConcurrentLinkedQueue<>();
        try {
            URL url = new URL("http://mosopen.ru/regions");
            URLConnection connection = url.openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            String content = new String(bufferedInputStream.readAllBytes());
            Document document = Jsoup.parse(content);
            List<Element> elements = document.select("strong").stream()
                    .filter(element -> element.text().length() == 1)
                    .collect(Collectors.toList());
            FileOutputStream fileOutputStream = new FileOutputStream(new File("downloadedFileDistrict.txt"));
            elements.forEach(element -> {
                var dictionary = element.text() + ": \n"; // буквы алфавита
                try {
                    fileOutputStream.write(dictionary.getBytes());
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                var links = ((Element) element.parentNode()).select("a");
                links.forEach(elementForLink -> {
                    try {
                        var description = elementForLink.attr("title");
                        var link = elementForLink.attr("href") + "\n";
                        var row = description + " - " + link;
                        fileOutputStream.write(row.getBytes());
                        concurrentLinkedQueueLink.add(link);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return concurrentLinkedQueueLink;
    }

    @Override
    public ConcurrentLinkedQueue<String> call() { // передаем список ссылок по районам
        return downloadInfoLinkToFile();
    }
}
