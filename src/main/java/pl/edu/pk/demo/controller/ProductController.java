package pl.edu.pk.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.model.Product;
import pl.edu.pk.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService productService){
        this.service = productService;
    }
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.createProduct(product);
    }
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
                          @RequestBody Product product) {
        return service.createProduct(product);
    }
}
