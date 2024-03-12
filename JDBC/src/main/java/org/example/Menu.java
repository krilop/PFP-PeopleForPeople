package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.example.crud.*;
public class Menu {

    private Menu()
    {
        throw new IllegalStateException("Utility class");
    }
    private static final Logger logger = LogManager.getLogger(Main.class);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void menu() throws IOException, SQLException
    {
        String caseTable;
        do {
            logger.info("\n\t\t\t--------- SELECT TABLE --------" +
                    "\n\t1 - Authorization" +
                    "\n\t2 - User_data" +
                    "\n\t3 - Contact_type" +
                    "\n\t4 - Interest_type" +
                    "\n\t5 - Relation_type" +
                    "\n\t6 - Relation" +
                    "\n\t7 - Contact" +
                    "\n\t8 - Interest_type_user" +
                    "\n\tAnother - cancel operation" +
                    "\n\tEnter num:\n");

            caseTable = br.readLine();
            switch (caseTable) {
                case "1" ->
                        CRUDAuthorization.crudAuthorization();/*
                case "2" ->
                        crudUserData();
                case "3" ->
                        crudContactType();
                case "4" ->
                        crudInterestType();
                case "5" ->
                        crudRelationType();
                case "6" ->
                        crudRelation();
                case "7" ->
                        crudContact();
                case "8" ->
                        crudInterestTypeUser();*/
                default -> {
                    logger.info("Exit");
                    return;
                }

            }
        } while (true);

    }
}
