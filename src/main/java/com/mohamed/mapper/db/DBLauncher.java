package com.mohamed.mapper.db;

import org.h2.tools.Server;

import java.sql.SQLException;

public class DBLauncher {
    public static void main(String[] args) {
        try {
            Server.main();
            System.out.println("DB Launched");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
