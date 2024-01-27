package org.nmu.shopmanagement.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nmu.shopmanagement.model.Category;
import org.nmu.shopmanagement.repository.CategoryRepository;
import org.nmu.shopmanagement.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Override
    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {
        Category oldCategory = categoryRepository.findById(id).orElseThrow();
        oldCategory.setCategoryName(category.getCategoryName());
        oldCategory.setProducts(category.getProducts());
        return categoryRepository.save(oldCategory);


    }

    @Override
    @Transactional
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }
}
