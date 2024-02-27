package org.nmu.shopmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"customer"})
@NoArgsConstructor
@Table(name = "persistedorder")
public class PersistedOrder {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "po_seq")
    @SequenceGenerator(
            name = "po_seq",
            sequenceName = "seq_persistedorder_id",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "ordered_at", updatable = false)
    private LocalDate orderedAt;
    @Column(name = "delivered_at")
    private LocalDate deliveredAt;
    @Column(name = "persistedorder_status")
    private String orderStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "persistedOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PersistedOrderItem> persistedOrderItems;

    public void addPersistedOrderItem(PersistedOrderItem item) {
        if (this.persistedOrderItems == null) {
            this.persistedOrderItems = new ArrayList<>();
        }
        item.setPersistedOrder(this);
        this.persistedOrderItems.add(item);
    }
}
