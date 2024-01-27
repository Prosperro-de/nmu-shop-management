package org.nmu.shopmanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.PersistedOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PersistedOrderService {
    PersistedOrder getPersistedOrder(Long id);
    List<PersistedOrder> getAllPersistedOrders();
    List<PersistedOrder> findPersistedOrderBetweenDates(LocalDate startDate, LocalDate endDate);
    List<PersistedOrder> findPersistedOrderForCustomerId(Long customerId);
    BigDecimal getMoneyIncomeBetweenDates(LocalDate startDate, LocalDate endDate);
    PersistedOrder createPersistedOrder(PersistedOrder persistedOrder);
    PersistedOrder updatePersistedOrder(Long id, PersistedOrder persistedOrder);
    void deletePersistedOrder(PersistedOrder persistedOrder);
    String getJson() throws JsonProcessingException;
}
