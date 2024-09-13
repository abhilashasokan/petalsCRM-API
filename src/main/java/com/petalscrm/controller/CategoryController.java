package com.petalscrm.controller;

import com.petalscrm.model.Category;
import com.petalscrm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create a new Category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.saveOrUpdateCategory(category);
        return ResponseEntity.ok(newCategory);
    }

    // Get all Categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        System.out.println("Get all categories");
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Get a Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Update a Category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCategoryName(categoryDetails.getCategoryName());
            category.setCategoryImage(categoryDetails.getCategoryImage());
            Category updatedCategory = categoryService.saveOrUpdateCategory(category);
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
