package pl.edu.pk.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.dto.ProductMapper;
import pl.edu.pk.demo.dto.ProductRequest;
import pl.edu.pk.demo.dto.ProductResponse;
import pl.edu.pk.demo.exception.ResourceNotFoundException;
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
    public List<ProductResponse> getAll() {
        return service.getAllProducts().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    // GET /api/products/{id} — pojedynczy lub 404
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return service.getProductById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Produkt o id=" + id + " nie istnieje"));
    }

    // POST /api/products — tworzenie nowego
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product created = service.createProduct(ProductMapper.toEntity(request));
        return ResponseEntity.status(201).body(ProductMapper.toResponse(created));
    }

    // PUT /api/products/{id} — aktualizacja
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                  @Valid @RequestBody ProductRequest request) {
        return service.updateProduct(id, ProductMapper.toEntity(request))
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Produkt o id=" + id + " nie istnieje"));
    }

    // DELETE /api/products/{id} — usuwanie lub 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteProduct(id)) {
            return ResponseEntity.noContent().build(); // 204
        }
        throw new ResourceNotFoundException("Produkt o id=" + id + " nie istnieje");
    }
}
