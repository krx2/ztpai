package pl.edu.pk.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pk.demo.exception.ResourceNotFoundException;
import pl.edu.pk.demo.model.Product;
import pl.edu.pk.demo.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    // Zadanie 2

    @Test
    void getProductById_returnsProduct_whenFound() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getProductById_throwsResourceNotFoundException_whenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(99L));
    }

    // Zadanie 3

    @Test
    void createProduct_returnsSavedProductWithId() {
        Product input = new Product();
        input.setName("Keyboard");
        input.setPrice(199.99);

        Product saved = new Product();
        saved.setId(1L);
        saved.setName("Keyboard");
        saved.setPrice(199.99);

        when(repository.save(any(Product.class))).thenReturn(saved);

        Product result = productService.createProduct(input);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_throwsIllegalArgumentException_whenNameIsNull() {
        Product product = new Product();
        product.setName(null);

        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        verify(repository, never()).save(any());
    }

    @Test
    void createProduct_throwsIllegalArgumentException_whenNameIsBlank() {
        Product product = new Product();
        product.setName("   ");

        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        verify(repository, never()).save(any());
    }
}
