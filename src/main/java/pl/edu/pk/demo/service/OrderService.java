package pl.edu.pk.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public double calculateTotal(List<Double> prices) {
        if (prices == null) {
            throw new IllegalArgumentException("Prices list cannot be null");
        }
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }
}
