package org.nmu.shopmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.repository.PersistedOrderRepository;
import org.nmu.shopmanagement.service.PersistedOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class PersistedOrderServiceImpl implements PersistedOrderService {

    private PersistedOrderRepository persistedOrderRepository;
    private ObjectMapper objectMapper;

    @Override
    @Transactional(readOnly = true)
    public PersistedOrder getPersistedOrder(Long id) {
        return persistedOrderRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersistedOrder> getAllPersistedOrders() {
        return persistedOrderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersistedOrder> findPersistedOrderBetweenDates(LocalDate startDate, LocalDate endDate) {
        return persistedOrderRepository.findPersistedOrderBetweenDates(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersistedOrder> findPersistedOrderForCustomerId(Long customerId) {
        return persistedOrderRepository.findPersistedOrderByCustomerId(customerId);
    }

    @Override
    public BigDecimal getMoneyIncomeBetweenDates(LocalDate startDate, LocalDate endDate) {
        return persistedOrderRepository.getMoneyIncomeBetweenDates(startDate, endDate);
    }

    @Override
    @Transactional
    public PersistedOrder createPersistedOrder(PersistedOrder persistedOrder) {
        return persistedOrderRepository.save(persistedOrder);
    }

    @Override
    @Transactional
    public PersistedOrder updatePersistedOrder(Long id, PersistedOrder persistedOrder) {
        PersistedOrder oldPersistedOrder = persistedOrderRepository.findById(id).orElseThrow();
        oldPersistedOrder.setCustomer(persistedOrder.getCustomer());
        oldPersistedOrder.setOrderStatus(persistedOrder.getOrderStatus());
        oldPersistedOrder.setDeliveredAt(persistedOrder.getDeliveredAt());
        oldPersistedOrder.setPersistedOrderItems(persistedOrder.getPersistedOrderItems());
        return persistedOrderRepository.save(oldPersistedOrder);
    }

    @Override
    @Transactional
    public void deletePersistedOrder(PersistedOrder persistedOrder) {
        persistedOrderRepository.delete(persistedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public String getJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(findPersistedOrderBetweenDates(
                LocalDate.of(2023, Month.JUNE, 1),
                LocalDate.of(2023, Month.AUGUST, 1)));
    }
}
