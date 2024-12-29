package com.chakshu.backend.daoImpl;

import com.chakshu.backend.dao.CategoryDao;
import com.chakshu.backend.entity.ApiRolePermission;
import com.chakshu.backend.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private EntityManager entityManager;

    @Autowired
    public CategoryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Category> getAllCategories() {
        Query query = entityManager.createQuery("Select cat from Category cat");
        List<Category> categoryList = query.getResultList();
        return categoryList;
    }

    public Category getCategoryById(Integer categoryId) {
        Query query = entityManager.createQuery("Select cat from Category cat where categoryId =:categoryId");
        query.setParameter("categoryId",categoryId);
        List<Category> categoryList = query.getResultList();
        if(!categoryList.isEmpty()) {
            return categoryList.get(0);
        }
        return null;
    }

}
