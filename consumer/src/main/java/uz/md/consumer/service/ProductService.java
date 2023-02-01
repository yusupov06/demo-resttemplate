package uz.md.consumer.service;

import uz.md.consumer.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product add(Product product);
}
