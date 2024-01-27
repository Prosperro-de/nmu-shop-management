package org.nmu.shopmanagement.repository;

import org.nmu.shopmanagement.model.PersistedOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistedOrderItemRepository extends JpaRepository<PersistedOrderItem, Long> {
}
