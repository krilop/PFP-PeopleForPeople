package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBFunctions {
    public Connection connect_to_db(String dbname, String user, String password){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,password);
            if(conn!=null)
                System.out.println("Successfully connect!");
            else
                System.out.println("Connection failed :(");
        }
        catch (Exception e)
        {
            System.out.println("Cannot connect to Database!");
        }
        return conn;
    }
}
