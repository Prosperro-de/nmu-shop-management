package org.nmu.shopmanagement.service;

import org.nmu.shopmanagement.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(Long id);
    List<Customer> getAllCustomers();
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Customer customer);
}
