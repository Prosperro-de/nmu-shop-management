package org.nmu.shopmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"supplier", "category"})
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq")
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "seq_product_id",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
}
