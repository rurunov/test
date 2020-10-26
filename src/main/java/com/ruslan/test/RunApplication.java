package com.ruslan.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class RunApplication {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> streets = new LinkedList<>();
        DownloadRegions downloadInfo = new DownloadRegions();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<ConcurrentLinkedQueue<String>> concurrentLinkedQueueFuture =
                executorService.submit(downloadInfo::downloadInfoLinkToFile);

        while (!concurrentLinkedQueueFuture.isDone()) {
            Thread.sleep(300);
            System.out.println("Calculating...");
        }

        executorService.shutdown();
        System.out.println("Finished counting districts: " + concurrentLinkedQueueFuture.get().size());
        System.out.println("Finished date: " + LocalDateTime.now());


        // запускаем в executor сборку ссылок по улицам
        executorService = Executors.newFixedThreadPool(4);
        Future<List<String>> future1 = executorService.submit(() -> {
            System.out.println("Started Future1: " + Thread.currentThread().getName());
            List<String> listStreet = new LinkedList<>();
            URL url = null;
            try {
                while (concurrentLinkedQueueFuture.get().size() != 0) {
                    url = new URL(concurrentLinkedQueueFuture.get().poll());
                    URLConnection urlConnection = url.openConnection();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String content = new String(bufferedInputStream.readAllBytes());

                    Document document = Jsoup.parse(content);
                    String link = document.select("li").select("a").stream()
                            .filter(element -> element.attr("href").contains("streets") && element.text().contains("Улицы района"))
                            .map(element -> element.attr("href"))
                            .collect(Collectors.toList()).get(0);
                    url = new URL(link);
                    urlConnection = url.openConnection();
                    bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    content = new String(bufferedInputStream.readAllBytes());
                    document = Jsoup.parse(content);
                    listStreet.addAll(document.select("div.double_part").select("li").select("a").stream()
                            .map(element -> element.attr("href"))
//                            .peek(linkForStreets -> System.out.println("Ling for Street -> " + linkForStreets))
                            .collect(Collectors.toList()));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return listStreet;
        });
        Future<List<String>> future2 = executorService.submit(() -> {
            System.out.println("Started Future2: " + Thread.currentThread().getName());
            List<String> listStreet = new LinkedList<>();
            URL url = null;
            try {
                while (concurrentLinkedQueueFuture.get().size() != 0) {
                    url = new URL(concurrentLinkedQueueFuture.get().poll());
                    URLConnection urlConnection = url.openConnection();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String content = new String(bufferedInputStream.readAllBytes());

                    Document document = Jsoup.parse(content);
                    String link = document.select("li").select("a").stream()
                            .filter(element -> element.attr("href").contains("streets") && element.text().contains("Улицы района"))
                            .map(element -> element.attr("href"))
                            .collect(Collectors.toList()).get(0);
                    url = new URL(link);
                    urlConnection = url.openConnection();
                    bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    content = new String(bufferedInputStream.readAllBytes());
                    document = Jsoup.parse(content);
                    listStreet.addAll(document.select("div.double_part").select("li").select("a").stream()
                            .map(element -> element.attr("href"))
//                            .peek(linkForStreets -> System.out.println("Ling for Street -> " + linkForStreets))
                            .collect(Collectors.toList()));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return listStreet;
        });
        Future<List<String>> future3 = executorService.submit(() -> {
            System.out.println("Started Future3: " + Thread.currentThread().getName());
            List<String> listStreet = new LinkedList<>();
            URL url = null;
            try {
                while (concurrentLinkedQueueFuture.get().size() != 0) {
                    url = new URL(concurrentLinkedQueueFuture.get().poll());
                    URLConnection urlConnection = url.openConnection();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String content = new String(bufferedInputStream.readAllBytes());

                    Document document = Jsoup.parse(content);
                    String link = document.select("li").select("a").stream()
                            .filter(element -> element.attr("href").contains("streets") && element.text().contains("Улицы района"))
                            .map(element -> element.attr("href"))
                            .collect(Collectors.toList()).get(0);
                    url = new URL(link);
                    urlConnection = url.openConnection();
                    bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    content = new String(bufferedInputStream.readAllBytes());
                    document = Jsoup.parse(content);
                    listStreet.addAll(document.select("div.double_part").select("li").select("a").stream()
                            .map(element -> element.attr("href"))
//                            .peek(linkForStreets -> System.out.println("Ling for Street -> " + linkForStreets))
                            .collect(Collectors.toList()));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return listStreet;
        });
        Future<List<String>> future4 = executorService.submit(() -> {
            System.out.println("Started Future4: " + Thread.currentThread().getName());
            List<String> listStreet = new LinkedList<>();
            URL url = null;
            try {
                while (concurrentLinkedQueueFuture.get().size() != 0) {
                    url = new URL(concurrentLinkedQueueFuture.get().poll());
                    URLConnection urlConnection = url.openConnection();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String content = new String(bufferedInputStream.readAllBytes());

                    Document document = Jsoup.parse(content);
                    String link = document.select("li").select("a").stream()
                            .filter(element -> element.attr("href").contains("streets") && element.text().contains("Улицы района"))
                            .map(element -> element.attr("href"))
                            .collect(Collectors.toList()).get(0);
                    url = new URL(link);
                    urlConnection = url.openConnection();
                    bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                    content = new String(bufferedInputStream.readAllBytes());
                    document = Jsoup.parse(content);
                    listStreet.addAll(document.select("div.double_part").select("li").select("a").stream()
                            .map(element -> element.attr("href"))
//                            .peek(linkForStreets -> System.out.println("Ling for Street -> " + linkForStreets))
                            .collect(Collectors.toList()));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return listStreet;
        });

        boolean result1 = streets.addAll(future1.get());
        System.out.println("Future1 added to streets: " + result1);
        boolean result2 = streets.addAll(future2.get());
        System.out.println("Future2 added to streets: " + result2);
        boolean result3 = streets.addAll(future3.get());
        System.out.println("Future3 added to streets: " + result3);
        boolean result4 = streets.addAll(future4.get());
        System.out.println("Future4 added to streets: " + result4);
        executorService.shutdown();

        System.out.println("Total streets in Moscow: " + streets.size());

    }
}
