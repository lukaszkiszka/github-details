package com.lkiszka.rt.githubdetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubDetailsApplication {

    public final static String API_VERSION = "v1";

    @Value("${repositories.connectionTimeout}")
    private int connectionTimeout;

    @Value("${repositories.readTimeout}")
    private int readTimeout;

    public static void main(String[] args) {
        SpringApplication.run(GithubDetailsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(connectionTimeout);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(readTimeout);

        return restTemplate;

    }
}
