package com.hz.infra.repository;

import com.hz.models.Product;

/**
 * Created by hoank92 on Jul, 2020
 */
public interface ProductRepository {
    Product getProductById(Integer id);
    void updateProduct(Product product);
    void insertProduct(Product product);
}
