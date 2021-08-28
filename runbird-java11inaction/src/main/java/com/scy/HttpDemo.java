package com.scy;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpDemo {

    private static final String targetUrl = "http://www.baidu.com";
    private static final URI uri = URI.create(targetUrl);

    private static final void get() {
        var httpClient = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder().timeout(Duration.ofMillis(2000))
//                .header("key","val")
//                .header("key","val")
                .uri(uri).build();

        try {
            var resp = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(resp);

            System.out.println(resp.body());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        get();
    }


}
