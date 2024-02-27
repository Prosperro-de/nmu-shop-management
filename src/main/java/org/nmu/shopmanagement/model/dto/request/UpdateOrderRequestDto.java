package org.nmu.shopmanagement.model.dto.request;


import org.nmu.shopmanagement.model.Customer;

import java.time.LocalDate;
import java.util.List;

public record UpdateOrderRequestDto(LocalDate deliveredAt,
                                    String orderStatus,
                                    Customer customer,
                                    List<OrderItemRequestDto> items) {
}