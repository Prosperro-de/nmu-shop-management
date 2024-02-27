package org.nmu.shopmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.model.PersistedOrderItem;
import org.nmu.shopmanagement.model.dto.request.CreateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.request.UpdateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.response.OrderResponseDto;
import org.nmu.shopmanagement.repository.CustomerRepository;
import org.nmu.shopmanagement.repository.PersistedOrderRepository;
import org.nmu.shopmanagement.repository.ProductRepository;
import org.nmu.shopmanagement.service.PersistedOrderService;
import org.nmu.shopmanagement.service.mapper.RequestMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class PersistedOrderServiceImpl implements PersistedOrderService {

    private PersistedOrderRepository persistedOrderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ObjectMapper objectMapper;
    private RequestMapper requestMapper;

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getPersistedOrder(Long id) {
        return requestMapper.toOrderResponseDto(persistedOrderRepository.findById(id).orElseThrow());
    }

    @Override
    public List<OrderResponseDto> getAllPersistedOrdersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return requestMapper.toOrderResponseDtoList(persistedOrderRepository.findAll(pageable).getContent());
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
    public OrderResponseDto createPersistedOrder(CreateOrderRequestDto request) {
        Customer customer = customerRepository.findById(request.customerId()).orElseThrow();
        PersistedOrder persistedOrder = new PersistedOrder();
        persistedOrder.setCustomer(customer);
        persistedOrder.setOrderStatus("Created");
        persistedOrder.setOrderedAt(LocalDate.now());
        List<PersistedOrderItem> items = request.items().stream()
                .map(requestItem -> {
                    PersistedOrderItem persistedOrderItem = new PersistedOrderItem();
                    persistedOrderItem.setProduct(productRepository.findById(requestItem.productId()).orElseThrow());
                    persistedOrderItem.setQuantity(requestItem.quantity());
                    persistedOrderItem.setPersistedOrder(persistedOrder);
                    return persistedOrderItem;
                })
                .toList();
        persistedOrder.setPersistedOrderItems(items);
        return requestMapper.toOrderResponseDto(persistedOrderRepository.save(persistedOrder));
    }

    @Override
    @Transactional
    public OrderResponseDto updatePersistedOrder(Long id, UpdateOrderRequestDto request) {
        PersistedOrder oldPersistedOrder = persistedOrderRepository.findById(id).orElseThrow();
        oldPersistedOrder.setCustomer(request.customer());
        oldPersistedOrder.setOrderStatus(request.orderStatus());
        oldPersistedOrder.setDeliveredAt(request.deliveredAt());
        oldPersistedOrder.setPersistedOrderItems(requestMapper.toPersistedOrderItemList(request.items()));
        return requestMapper.toOrderResponseDto(persistedOrderRepository.save(oldPersistedOrder));
    }

    @Override
    @Transactional
    public void deletePersistedOrder(Long id) {
        persistedOrderRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public String getJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(findPersistedOrderBetweenDates(
                LocalDate.of(2023, Month.JUNE, 1),
                LocalDate.of(2023, Month.AUGUST, 1)));
    }
}
