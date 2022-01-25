package com.mohamed.mapper;

import com.mohamed.mapper.model.Product;
import com.mohamed.mapper.orm.EntityManager;

public class ReadingObjects {

    public static void main(String[] args) throws Exception {

        EntityManager<Product> entityManager = EntityManager.of(Product.class);

        Product product1 = entityManager.find(Product.class, 1L);
        Product product2 = entityManager.find(Product.class, 2L);
        Product product3 = entityManager.find(Product.class, 3L);
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
    }
}
