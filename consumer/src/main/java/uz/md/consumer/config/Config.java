package uz.md.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import static uz.md.consumer.config.LoggingInterceptor.LOGGER;

@Configuration
public class Config {

    private static final String PROXY_SERVER_HOST = "127.0.0.1";
    private static final int PROXY_SERVER_PORT = 8087;

    @Bean
    public RestTemplate getRestTemplate() {

        RestTemplate restTemplate ;
        if (LOGGER.isDebugEnabled()) {

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_SERVER_HOST, PROXY_SERVER_PORT));

            System.out.println("Proxy: " + proxy);
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(proxy);
            restTemplate = new RestTemplate(requestFactory);
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
