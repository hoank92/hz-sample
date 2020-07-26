package com.hz.infra.mysql;

import com.hazelcast.core.MapStore;
import com.hz.models.Product;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * Created by hoank92 on Jul, 2020
 */
@AllArgsConstructor
public class MysqlMapStore implements MapStore<Integer, String> {
    private final MySqlProductStorage productRepository;

    @Override
    public void store(Integer id, String name) {
        productRepository.updateProduct(Product.builder()
                .id(id)
                .name(name)
                .build());
    }

    @Override
    public void storeAll(Map<Integer, String> map) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void deleteAll(Collection<Integer> collection) {

    }

    @Override
    public String load(Integer id) {
        return productRepository.getProductById(id).getName();
    }

    @Override
    public Map<Integer, String> loadAll(Collection<Integer> collection) {
        return null;
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        return null;
    }
}
