package com.hz.infra.mysql;

import com.hz.infra.mysql.dao.ProductDAO;
import com.hz.models.Product;
import lombok.NonNull;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

/**
 * Created by hoank92 on Jul, 2020
 */
public class MySqlProductStorage extends MySqlStorage {
    public MySqlProductStorage(@NonNull Jdbi jdbi) {
        super(jdbi);
    }

    public Product getProductById(int id) {
        return this.openDaoAndApply(ProductDAO.class, productDAO -> productDAO.getProductById(id));
    }

    public void updateProduct(Product product) {
        this.openDaoAndAccept(ProductDAO.class, dao -> dao.updateProduct(List.of(product)));
    }

    public void insertProduct(Product product) {
        this.openDaoAndAccept(ProductDAO.class, dao -> dao.insertProduct(List.of(product)));
    }
}
