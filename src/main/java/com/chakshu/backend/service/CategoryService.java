package com.chakshu.backend.service;


import com.chakshu.backend.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    public List<Category> getAllCategories();

    public Category getCategoryById(Integer categoryId);

}
