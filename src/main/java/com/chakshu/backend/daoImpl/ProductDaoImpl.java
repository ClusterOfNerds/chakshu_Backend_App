package com.chakshu.backend.daoImpl;

import com.chakshu.backend.dao.ProductDao;
import com.chakshu.backend.entity.Category;
import com.chakshu.backend.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private EntityManager entityManager;

    @Autowired
    public ProductDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Product getProductById (Integer productId) {
        try {
            Query query = entityManager.createQuery("Select product from Product product where productId =:productId");
            query.setParameter("productId",productId);
            List<Product> productList = query.getResultList();
            if(!productList.isEmpty()) {
                return productList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
