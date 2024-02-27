package org.nmu.shopmanagement.service;

import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.dto.request.CustomerRequestDto;
import org.nmu.shopmanagement.model.dto.response.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto getCustomer(Long id);
    List<CustomerResponseDto> getAllCustomers();
    List<CustomerResponseDto> getAllCustomersWithPagination(int page, int size);
    CustomerResponseDto createCustomer(CustomerRequestDto request);
    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto request);
    void deleteCustomer(Long id);
}
