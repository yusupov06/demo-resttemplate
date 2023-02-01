package uz.md.consumer.service;

import org.springframework.stereotype.Service;
import uz.md.consumer.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products;

    public ProductServiceImpl(){
        products = new ArrayList<>(List.of(
                new Product("product1", "description1", 10000.0),
                new Product("product2", "description2", 11000.0),
                new Product("product3", "description3", 12000.0),
                new Product("product4", "description4", 13000.0)
        ));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product add(Product product) {
        products.add(product);
        return product;
    }
}
