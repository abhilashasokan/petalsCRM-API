package com.petalscrm.service;

import com.petalscrm.model.Category;
import com.petalscrm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create or Update a Category
    public Category saveOrUpdateCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get all Categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get a Category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Delete a Category by ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
