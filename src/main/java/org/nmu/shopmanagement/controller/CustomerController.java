package org.nmu.shopmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.nmu.shopmanagement.model.dto.request.CustomerRequestDto;
import org.nmu.shopmanagement.model.dto.response.CustomerResponseDto;
import org.nmu.shopmanagement.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@Tags(value = {
        @Tag(name = "Customer", description = "Customer management API")
})
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping(
            produces = APPLICATION_JSON_VALUE,
            consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new customer", responses = {
            @ApiResponse(description = "Successfully created", responseCode = "201")
    })
    public CustomerResponseDto createCustomer(@RequestBody CustomerRequestDto customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping(value = "/{id}",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve customer by id", responses = {
            @ApiResponse(description = "Successfully retrieved", responseCode = "200")
    })
    public CustomerResponseDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping(value = "/{id}",
            produces = APPLICATION_JSON_VALUE,
            consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update customer by id", responses = {
            @ApiResponse(description = "Successfully updated", responseCode = "200")
    })
    public CustomerResponseDto updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete customer by id", responses = {
            @ApiResponse(description = "Successfully deleted", responseCode = "204")
    })
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping(value = "/all",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve customers for specific size", responses = {
            @ApiResponse(description = "Successfully retrieved", responseCode = "200")
    })
    public List<CustomerResponseDto> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return customerService.getAllCustomersWithPagination(page, size);
    }
}
