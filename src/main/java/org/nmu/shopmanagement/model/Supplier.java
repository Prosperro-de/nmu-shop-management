package org.nmu.shopmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supplier_seq")
    @SequenceGenerator(
            name = "supplier_seq",
            sequenceName = "seq_supplier_id",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "street_name")
    private String streetName;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private String phone;


    @OneToMany(mappedBy = "supplier")
    private Set<Product> products;
}
