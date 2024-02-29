package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBFunctions {
    private static final Logger logger = LogManager.getLogger(DBFunctions.class);
    public Connection connectToDB(String dbname, String user, String password){
        Connection conn = null;
        try{
         //   Class.forName("org.postgresql.Driver");
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
