package org.nmu.shopmanagement.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@ToString(exclude = {"persistedOrders"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_seq")
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "seq_customer_id",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_address")
    private String address;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PersistedOrder> persistedOrders;

}
