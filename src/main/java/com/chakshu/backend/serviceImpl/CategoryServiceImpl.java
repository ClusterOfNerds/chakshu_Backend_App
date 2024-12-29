package com.chakshu.backend.serviceImpl;

import com.chakshu.backend.dao.CategoryDao;
import com.chakshu.backend.entity.Category;
import com.chakshu.backend.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public Category getCategoryById(Integer categoryId) { return categoryDao.getCategoryById(categoryId); }

}
