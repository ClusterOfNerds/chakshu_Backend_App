package com.chakshu.backend.controller;

import com.chakshu.backend.entity.Category;
import com.chakshu.backend.service.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiInfo/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {

        try{
            List<Category> categoryList = categoryService.getAllCategories();
            System.out.println(categoryList);
            return ResponseEntity.ok(categoryList);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/details")
    public ResponseEntity<?> getCategoryById(@RequestParam Integer categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
