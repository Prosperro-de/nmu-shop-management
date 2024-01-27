package org.nmu.shopmanagement.service;

import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.model.PersistedOrderItem;

import java.util.List;

public interface PersistedOrderItemService {
    PersistedOrderItem getPersistedOrderItem(Long id);
    List<PersistedOrderItem> getAllPersistedOrderItems();
    PersistedOrderItem createPersistedOrderItem(PersistedOrderItem persistedOrderItem);
    PersistedOrderItem updatePersistedOrderItem(Long id, PersistedOrderItem persistedOrderItem);
    void deletePersistedOrderItem(PersistedOrderItem persistedOrderItem);
}
