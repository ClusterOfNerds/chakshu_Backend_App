package com.chakshu.backend.serviceImpl;


import com.chakshu.backend.dao.ProductDao;
import com.chakshu.backend.entity.Product;
import com.chakshu.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl (ProductDao productDao) { this.productDao = productDao; }

    public Product getProductById (Integer productId) { return productDao.getProductById(productId); }
}
