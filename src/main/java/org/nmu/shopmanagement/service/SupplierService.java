package org.nmu.shopmanagement.service;

import org.nmu.shopmanagement.model.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier getSupplier(Long id);
    List<Supplier> getAllSuppliers();
    Supplier createSupplier(Supplier supplier);
    Supplier updateSupplier(Long id, Supplier supplier);
    void deleteSupplier(Supplier supplier);
}
