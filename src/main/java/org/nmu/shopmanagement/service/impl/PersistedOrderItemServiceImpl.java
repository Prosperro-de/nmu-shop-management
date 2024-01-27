package org.nmu.shopmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.PersistedOrder;
import org.nmu.shopmanagement.model.PersistedOrderItem;
import org.nmu.shopmanagement.repository.PersistedOrderItemRepository;
import org.nmu.shopmanagement.service.PersistedOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class PersistedOrderItemServiceImpl implements PersistedOrderItemService {

    private PersistedOrderItemRepository persistedOrderItemRepository;

    @Override
    @Transactional(readOnly = true)
    public PersistedOrderItem getPersistedOrderItem(Long id) {
        return persistedOrderItemRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersistedOrderItem> getAllPersistedOrderItems() {
        return persistedOrderItemRepository.findAll();
    }

    @Override
    @Transactional
    public PersistedOrderItem createPersistedOrderItem(PersistedOrderItem persistedOrder) {
        return persistedOrderItemRepository.save(persistedOrder);
    }

    @Override
    @Transactional
    public PersistedOrderItem updatePersistedOrderItem(Long id, PersistedOrderItem persistedOrder) {
        PersistedOrderItem oldPersistedOrderItem = persistedOrderItemRepository.findById(id).orElseThrow();
        oldPersistedOrderItem.setProduct(persistedOrder.getProduct());
        oldPersistedOrderItem.setQuantity(persistedOrder.getQuantity());
        oldPersistedOrderItem.setPersistedOrder(persistedOrder.getPersistedOrder());

        return persistedOrderItemRepository.save(oldPersistedOrderItem);
    }

    @Override
    @Transactional
    public void deletePersistedOrderItem(PersistedOrderItem persistedOrderItem) {
        persistedOrderItemRepository.delete(persistedOrderItem);
    }
}
