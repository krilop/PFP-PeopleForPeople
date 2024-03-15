package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFunctions {
    private static final Logger logger = LogManager.getLogger(DBFunctions.class);
    public Connection connectToDB(String dbname, String user, String password) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        try{
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,password);
            logger.info("Successfully connect!");
        }
        catch (Exception e)
        {
            logger.info("Cannot connect to Database!");
            logger.error(e);
        }
        return conn;
    }
}
