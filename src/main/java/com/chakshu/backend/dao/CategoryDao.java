package com.chakshu.backend.dao;

import com.chakshu.backend.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryDao {

    public List<Category> getAllCategories();

    public Category getCategoryById(Integer categoryId);

}
