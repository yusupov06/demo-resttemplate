package uz.md.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uz.md.consumer.entity.Product;
import uz.md.consumer.feignClients.ProducerServiceClient;

import java.util.List;

@RestController
@RequestMapping("/client/product")
@Slf4j
public class ProducerClient {

    private final RestTemplate restTemplate;

    private final ProducerServiceClient producerServiceClient;

    public static final String PRODUCER_SERVICE_URL = "http://localhost:8081/product";

    public ProducerClient(RestTemplate restTemplate, ProducerServiceClient producerServiceClient) {
        this.restTemplate = restTemplate;
        this.producerServiceClient = producerServiceClient;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        String host = restTemplate.getUriTemplateHandler().expand("/").getHost();
        int port = restTemplate.getUriTemplateHandler().expand("/").getPort();
        log.info("Host: {} Port: {}", host, port);
        ResponseEntity<List<Product>> response = restTemplate
                .exchange(PRODUCER_SERVICE_URL,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        new ParameterizedTypeReference<>() {
                        });

        return ResponseEntity
                .status(HttpStatus.valueOf(200))
                .body(response.getBody());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllByFeign() {
        return producerServiceClient.allProducts();
    }

    @GetMapping("/add")
    public ResponseEntity<Product> addAndReturn(@RequestBody Product product) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(PRODUCER_SERVICE_URL)
                .queryParam("name", product.getName())
                .queryParam("description", product.getDescription())
                .queryParam("price", product.getPrice());

        return restTemplate
                .exchange(builder.toUriString(),
                        HttpMethod.POST,
                        HttpEntity.EMPTY,
                        Product.class);
    }

}
