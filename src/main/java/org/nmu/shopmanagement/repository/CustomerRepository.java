package org.nmu.shopmanagement.repository;

import org.nmu.shopmanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
