package org.nmu.shopmanagement.service;

import org.nmu.shopmanagement.model.Category;
import org.nmu.shopmanagement.model.Customer;

import java.util.List;

public interface CategoryService {
    Category getCategory(Long id);
    List<Category> getAllCategory();
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Category category);
}
