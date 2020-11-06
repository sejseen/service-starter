package com.ktyma.portalservice.adapter.external.rest;

import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

@Component
public class HTTPRequestSender {

    private final WebClient customWebClient;

    public HTTPRequestSender(@Qualifier(value = "customWebClient") WebClient customWebClient) {
        this.customWebClient = customWebClient;
    }

    public ResponseSpec post(String url, Object body) {
        return customWebClient
            .post()
            .uri(url)
            .body(BodyInserters.fromValue(body))
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .acceptCharset(StandardCharsets.UTF_8)
            .ifNoneMatch("*")
            .retrieve();
    }

    public ResponseSpec post(String url) {
        return customWebClient
            .post()
            .uri(url)
            .body(BodyInserters.empty())
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .acceptCharset(StandardCharsets.UTF_8)
            .ifNoneMatch("*")
            .retrieve();
    }

    public ResponseSpec get(String url) {
        return customWebClient
            .get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .acceptCharset(StandardCharsets.UTF_8)
            .ifNoneMatch("*")
            .retrieve();
    }

}
