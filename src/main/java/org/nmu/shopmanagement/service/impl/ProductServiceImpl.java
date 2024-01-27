package org.nmu.shopmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Product;
import org.nmu.shopmanagement.repository.ProductRepository;
import org.nmu.shopmanagement.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow();
        oldProduct.setName(product.getName());
        oldProduct.setUnitPrice(product.getUnitPrice());
        oldProduct.setQuantityInStock(product.getQuantityInStock());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setSupplier(product.getSupplier());
        return productRepository.save(oldProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
