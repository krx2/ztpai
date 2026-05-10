package pl.edu.pk.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void calculateTotal_returnsSumForGivenPrices() {
        double result = orderService.calculateTotal(List.of(10.0, 20.0, 30.0));
        assertEquals(60.0, result);
    }

    @Test
    void calculateTotal_returnsZeroForEmptyList() {
        double result = orderService.calculateTotal(Collections.emptyList());
        assertEquals(0.0, result);
    }

    @Test
    void calculateTotal_throwsWhenPricesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.calculateTotal(null));
    }
}
