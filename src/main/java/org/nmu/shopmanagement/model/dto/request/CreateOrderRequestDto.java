package org.nmu.shopmanagement.model.dto.request;

import java.util.List;

public record CreateOrderRequestDto (Long customerId, List<OrderItemRequestDto> items) {
}
