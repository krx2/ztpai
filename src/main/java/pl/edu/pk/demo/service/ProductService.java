package pl.edu.pk.demo.service;

import org.springframework.stereotype.Service;
import pl.edu.pk.demo.model.Product;
import pl.edu.pk.demo.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Product createProduct(Product product) {
        return repository.save(product);
    }
}
