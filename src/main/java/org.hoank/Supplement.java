package org.hoank;

import lombok.ToString;

import java.io.Serializable;

/**
 * Created by hoank92 on Sep, 2019
 */
@ToString
class Supplement implements Serializable {

    private final String name;
    private final Integer price;

    Supplement(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}