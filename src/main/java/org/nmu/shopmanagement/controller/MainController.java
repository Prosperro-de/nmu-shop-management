package org.nmu.shopmanagement.controller;

import lombok.AllArgsConstructor;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.model.Product;
import org.nmu.shopmanagement.service.CustomerService;
import org.nmu.shopmanagement.service.PersistedOrderService;
import org.nmu.shopmanagement.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class MainController {
    private PersistedOrderService persistedOrderService;
    private CustomerService customerService;
    private ProductService productService;

    @GetMapping("/orders")
    public List<PersistedOrder> getOrders() {
        return persistedOrderService.getAllPersistedOrders();
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/orders/between-dates")
    public List<PersistedOrder> findOrdersBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return persistedOrderService.findPersistedOrderBetweenDates(startDate, endDate);
    }

    @GetMapping("/orders/customer")
    public List<PersistedOrder> findOrdersForCustomer(@RequestParam Long id) {
        return persistedOrderService.findPersistedOrderForCustomerId(id);
    }

    @GetMapping("/revenue/between-dates")
    public BigDecimal findOrdersForCustomer(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return persistedOrderService.getMoneyIncomeBetweenDates(startDate, endDate);
    }
}
