package uz.md.consumer.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.md.consumer.config.FeignConfig;
import uz.md.consumer.entity.Product;

import java.util.List;


@FeignClient(name = "producerServiceClient", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface ProducerServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/product")
    ResponseEntity<List<Product>> allProducts();

}
