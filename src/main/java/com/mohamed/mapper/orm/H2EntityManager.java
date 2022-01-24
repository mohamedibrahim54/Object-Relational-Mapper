package com.mohamed.mapper.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManager<T> {
    @Override
    protected Connection buildConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:D:\\My Work\\pluralsight\\Object Relational Mapping\\src\\main\\resources\\db-files\\products-db",
                "sa", "");
        return connection;
    }
}
