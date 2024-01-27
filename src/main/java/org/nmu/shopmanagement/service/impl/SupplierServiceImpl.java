package org.nmu.shopmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Supplier;
import org.nmu.shopmanagement.repository.SupplierRepository;
import org.nmu.shopmanagement.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    @Override
    @Transactional(readOnly = true)
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    @Transactional
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier oldSupplier = supplierRepository.findById(id).orElseThrow();
        oldSupplier.setSupplierName(supplier.getSupplierName());
        oldSupplier.setContactName(supplier.getContactName());
        oldSupplier.setStreetName(supplier.getStreetName());
        oldSupplier.setCity(supplier.getCity());
        oldSupplier.setPostalCode(supplier.getPostalCode());
        oldSupplier.setCountry(supplier.getCountry());
        oldSupplier.setPhone(supplier.getPhone());
        return supplierRepository.save(oldSupplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
}
