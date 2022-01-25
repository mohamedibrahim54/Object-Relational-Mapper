package com.mohamed.mapper.orm;

import com.mohamed.mapper.model.Product;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager<T> {

    static <T> EntityManager<T> of(Class<T> aClass) {
        return new H2EntityManager<>();
    }

    void persist(T t) throws SQLException, IllegalAccessException;

    T find(Class<T> aClass, Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
