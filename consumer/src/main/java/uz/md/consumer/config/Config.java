package uz.md.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static uz.md.consumer.config.LoggingInterceptor.LOGGER;

@Configuration
public class Config {

    @Bean
    public RestTemplate getRestClient() {

        RestTemplate restTemplate;
        if (LOGGER.isDebugEnabled()) {
            ClientHttpRequestFactory factory
                    = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
            restTemplate = new RestTemplate(factory);
        } else {
            restTemplate = new RestTemplate();
        }

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new LoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }



}
