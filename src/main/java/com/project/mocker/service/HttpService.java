package com.project.mocker.service;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpService {
    public String get(String url) throws IOException, InterruptedException {
        // Create HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();
        // Create HttpRequest
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        // Send the request asynchronously
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        // Get the response body
        return httpResponse.body();
    }
}

