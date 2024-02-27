package org.nmu.shopmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.nmu.shopmanagement.model.dto.request.CreateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.request.UpdateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.response.OrderResponseDto;
import org.nmu.shopmanagement.service.PersistedOrderService;
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
@RequestMapping("/order")
@AllArgsConstructor
@Tags(value = {
        @Tag(name = "Order", description = "Order management API")
})
public class OrderController {
    private final PersistedOrderService orderService;

    @PostMapping(
            produces = APPLICATION_JSON_VALUE,
            consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new order", responses = {
            @ApiResponse(description = "Successfully created", responseCode = "201")
    })
    public OrderResponseDto createOrder(@RequestBody CreateOrderRequestDto customer) {
        return orderService.createPersistedOrder(customer);
    }

    @GetMapping(value = "/{id}",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve order by id", responses = {
            @ApiResponse(description = "Successfully retrieved", responseCode = "200")
    })
    public OrderResponseDto getOrder(@PathVariable Long id) {
        return orderService.getPersistedOrder(id);
    }

    @PutMapping(value = "/{id}",
            produces = APPLICATION_JSON_VALUE,
            consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update order by id", responses = {
            @ApiResponse(description = "Successfully updated", responseCode = "200")
    })
    public OrderResponseDto updateOrder(@PathVariable Long id, @RequestBody UpdateOrderRequestDto orderRequest) {
        return orderService.updatePersistedOrder(id, orderRequest);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete order by id", responses = {
            @ApiResponse(description = "Successfully deleted", responseCode = "204")
    })
    public void deleteOrder(@PathVariable Long id) {
        orderService.deletePersistedOrder(id);
    }

    @GetMapping(value = "/all",
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve orders for specific size", responses = {
            @ApiResponse(description = "Successfully retrieved", responseCode = "200")
    })
    public List<OrderResponseDto> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return orderService.getAllPersistedOrdersWithPagination(page, size);
    }
}
