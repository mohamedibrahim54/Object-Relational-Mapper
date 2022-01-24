package com.mohamed.mapper.orm;

import com.mohamed.mapper.model.Product;

import java.sql.SQLException;

public interface EntityManager<T> {

    static EntityManager<Product> of(Class<?> aClass) {
        return new H2EntityManager<>();
    }

    void persist(T t) throws SQLException, IllegalAccessException;
}
