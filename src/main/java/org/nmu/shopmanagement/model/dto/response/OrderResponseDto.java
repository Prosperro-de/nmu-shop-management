package org.nmu.shopmanagement.model.dto.response;

import org.nmu.shopmanagement.model.PersistedOrderItem;

import java.time.LocalDate;
import java.util.List;

public record OrderResponseDto(Long id,
    LocalDate orderedAt,
    LocalDate deliveredAt,
    String orderStatus,
    CustomerResponseDto customer,
    List<PersistedOrderItem> persistedOrderItems) {
}
