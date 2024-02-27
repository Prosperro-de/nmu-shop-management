package org.nmu.shopmanagement.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nmu.shopmanagement.model.Customer;
import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.model.PersistedOrderItem;
import org.nmu.shopmanagement.model.dto.request.CreateOrderRequestDto;
import org.nmu.shopmanagement.model.dto.request.CustomerRequestDto;
import org.nmu.shopmanagement.model.dto.request.OrderItemRequestDto;
import org.nmu.shopmanagement.model.dto.response.CustomerResponseDto;
import org.nmu.shopmanagement.model.dto.response.OrderResponseDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestMapper {

    CustomerResponseDto toCustomerResponseDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "persistedOrders", ignore = true)
    Customer toCustomer(CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> toCustomerResponseDtoList(List<Customer> customers);

    OrderResponseDto toOrderResponseDto(PersistedOrder persistedOrder);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "persistedOrderItems", source = "items")
    PersistedOrder toPersistedOrder(CreateOrderRequestDto orderRequestDto);
    List<OrderResponseDto> toOrderResponseDtoList(List<PersistedOrder> persistedOrders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "persistedOrder", ignore = true)
    PersistedOrderItem toPersistedOrderItem(OrderItemRequestDto itemRequest);

    List<PersistedOrderItem> toPersistedOrderItemList(List<OrderItemRequestDto> itemRequests);


}
