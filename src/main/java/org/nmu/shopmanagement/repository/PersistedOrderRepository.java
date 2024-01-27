package org.nmu.shopmanagement.repository;

import org.nmu.shopmanagement.model.PersistedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PersistedOrderRepository extends JpaRepository<PersistedOrder, Long> {

    @Query("SELECT po FROM PersistedOrder po JOIN FETCH po.persistedOrderItems WHERE po.deliveredAt BETWEEN :startDate AND :endDate ORDER BY po.deliveredAt")
    List<PersistedOrder> findPersistedOrderBetweenDates(LocalDate startDate, LocalDate endDate);

    @Query("SELECT po FROM PersistedOrder po JOIN FETCH po.persistedOrderItems WHERE po.customer.id = :customerId")
    List<PersistedOrder> findPersistedOrderByCustomerId(Long customerId);

    @Query("SELECT SUM(poi.product.unitPrice) FROM PersistedOrder po JOIN po.persistedOrderItems poi WHERE po.deliveredAt BETWEEN :startDate AND :endDate")
    BigDecimal getMoneyIncomeBetweenDates(LocalDate startDate, LocalDate endDate);

}
