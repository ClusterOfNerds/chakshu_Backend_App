package com.chakshu.backend.controller;


import com.chakshu.backend.entity.Product;
import com.chakshu.backend.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiInfo/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/details")
    public ResponseEntity<?> getProductById (@RequestParam Integer productId) {
        try {
            Product product = productService.getProductById(productId);
            if(product == null) {
                ResponseEntity.ok("Product not found");
            }
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception occured while getting product");
    }

}
