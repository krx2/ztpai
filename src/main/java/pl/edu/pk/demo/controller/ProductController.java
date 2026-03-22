package pl.edu.pk.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.model.Product;
import pl.edu.pk.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    // GET /api/products — lista wszystkich
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // GET /api/products/{id} — pojedynczy lub 404
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return service.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/products — tworzenie nowego
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = service.createProduct(product);
        return ResponseEntity.status(201).body(created);
    }

    // PUT /api/products/{id} — aktualizacja
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @RequestBody Product product) {
        return service.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/products/{id} — usuwanie lub 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteProduct(id)) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }
}