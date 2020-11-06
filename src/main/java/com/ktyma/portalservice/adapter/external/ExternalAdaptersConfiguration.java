package com.ktyma.portalservice.adapter.external;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExternalAdaptersConfiguration {

    @Bean
    public WebClient customWebClient(@Value("${api.base-url}") String apiBaseUrl) {
        return WebClient
            .builder()
            .baseUrl(apiBaseUrl)
            .defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

}
