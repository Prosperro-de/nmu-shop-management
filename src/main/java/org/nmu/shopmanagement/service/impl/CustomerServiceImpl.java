package org.nmu.shopmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.dto.request.CustomerRequestDto;
import org.nmu.shopmanagement.model.dto.response.CustomerResponseDto;
import org.nmu.shopmanagement.repository.CustomerRepository;
import org.nmu.shopmanagement.service.CustomerService;
import org.nmu.shopmanagement.service.mapper.RequestMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private RequestMapper requestMapper;

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomer(Long id) {
        return requestMapper.toCustomerResponseDto(customerRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> getAllCustomers() {
        return requestMapper.toCustomerResponseDtoList(customerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDto> getAllCustomersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return requestMapper.toCustomerResponseDtoList(customerRepository.findAll(pageable).getContent());
    }

    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        return requestMapper.toCustomerResponseDto(customerRepository.save(requestMapper.toCustomer(customerRequestDto)));
    }

    @Override
    @Transactional
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto request) {
        Customer oldCustomer = customerRepository.findById(id).orElseThrow();
        oldCustomer.setName(request.name());
        oldCustomer.setAddress(request.address());
        oldCustomer.setCity(request.city());
        oldCustomer.setPostalCode(request.postalCode());
        oldCustomer.setCountry(request.country());
        oldCustomer.setPhone(request.phone());
        return requestMapper.toCustomerResponseDto(customerRepository.save(oldCustomer));
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
