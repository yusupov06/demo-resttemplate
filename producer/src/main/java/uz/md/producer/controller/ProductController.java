package uz.md.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.md.producer.entity.Product;
import uz.md.producer.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/add")
    public ResponseEntity<Product> addAndReturn(@RequestBody Product product) {
        return new ResponseEntity<>(productService.add(product), HttpStatusCode.valueOf(201));
    }

}
