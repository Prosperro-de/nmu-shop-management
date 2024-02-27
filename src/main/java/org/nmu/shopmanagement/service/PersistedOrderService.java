package org.nmu.shopmanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.model.dto.request.CreateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.request.UpdateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.response.OrderResponseDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PersistedOrderService {
    OrderResponseDto getPersistedOrder(Long id);
    List<OrderResponseDto> getAllPersistedOrdersWithPagination(int page, int size);
    List<PersistedOrder> getAllPersistedOrders();
    List<PersistedOrder> findPersistedOrderBetweenDates(LocalDate startDate, LocalDate endDate);
    List<PersistedOrder> findPersistedOrderForCustomerId(Long customerId);
    BigDecimal getMoneyIncomeBetweenDates(LocalDate startDate, LocalDate endDate);
    OrderResponseDto createPersistedOrder(CreateOrderRequestDto request);
    OrderResponseDto updatePersistedOrder(Long id, UpdateOrderRequestDto request);
    void deletePersistedOrder(Long id);
    String getJson() throws JsonProcessingException;
}
