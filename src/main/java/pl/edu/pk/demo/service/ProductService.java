package pl.edu.pk.demo.service;

import org.springframework.stereotype.Service;
import pl.edu.pk.demo.model.Product;
import pl.edu.pk.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product updated) {
        return repository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setDescription(updated.getDescription());
            return repository.save(existing);
        });
    }

    public boolean deleteProduct(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}