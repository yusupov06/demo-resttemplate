package uz.md.producer.service;

import uz.md.producer.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product add(Product product);

}
