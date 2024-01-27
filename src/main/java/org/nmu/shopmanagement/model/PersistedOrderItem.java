package org.nmu.shopmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"persistedOrder", "product"})
@NoArgsConstructor
@Table(name = "persistedorderitem")
public class PersistedOrderItem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "poi_seq")
    @SequenceGenerator(
            name = "poi_seq",
            sequenceName = "seq_persistedorderitem_id",
            allocationSize = 1
    )
    private Long id;
    private Integer quantity;

    @JsonIgnore
    @ManyToOne(optional = false,  fetch = FetchType.LAZY)
    @JoinColumn(name = "persistedorder_id")
    private PersistedOrder persistedOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
