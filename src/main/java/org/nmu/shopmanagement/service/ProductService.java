package org.nmu.shopmanagement.service;

import org.nmu.shopmanagement.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Product product);
}
