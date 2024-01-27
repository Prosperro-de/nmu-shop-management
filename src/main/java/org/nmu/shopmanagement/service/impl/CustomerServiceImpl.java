package org.nmu.shopmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.repository.CustomerRepository;
import org.nmu.shopmanagement.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, Customer customer) {
        Customer oldCustomer = customerRepository.findById(id).orElseThrow();
        oldCustomer.setName(customer.getName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setCity(customer.getCity());
        oldCustomer.setPostalCode(customer.getPostalCode());
        oldCustomer.setCountry(customer.getCountry());
        oldCustomer.setPhone(customer.getPhone());
        return customerRepository.save(oldCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
