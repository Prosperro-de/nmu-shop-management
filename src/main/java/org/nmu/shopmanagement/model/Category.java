package org.nmu.shopmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_seq")
    @SequenceGenerator(
            name = "category_seq",
            sequenceName = "seq_category_id",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
