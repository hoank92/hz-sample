package com.hz.infra.mysql.dao;

import com.hz.models.Product;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.Collection;

/**
 * Created by hoank92 on Jul, 2020
 */
public interface ProductDAO {
    @RegisterBeanMapper(Product.class)
    @SqlQuery("SELECT `id`, `name`, `price`, `quantity` FROM product WHERE `id` = :id")
    Product getProductById(@Bind("id") int id);

    @SqlBatch("UPDATE product SET `name`= :name, `price` = :price, `quantity` = :quantity WHERE id = :id")
    int[] updateProduct(@BindBean Collection<Product> dtos);

    @SqlBatch("INSERT INTO product(`name`, `price`, `quantity`) VALUES (:name, :price, :quantity)")
    void insertProduct(@BindBean Collection<Product> dtos);
}
