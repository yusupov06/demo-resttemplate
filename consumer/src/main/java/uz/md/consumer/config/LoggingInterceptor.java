package uz.md.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    static Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex) throws IOException {

        LOGGER.info("Request body: {}", new String(reqBody, StandardCharsets.UTF_8));

        ClientHttpResponse response = ex.execute(req, reqBody);

//        if (LOGGER.isDebugEnabled()) {
        InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(isr)
                .lines()
                .collect(Collectors.joining("\n"));
        LOGGER.info("Response body: {}", body);
//        }

        return response;
    }
}