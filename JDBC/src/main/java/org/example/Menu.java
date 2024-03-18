package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.crud.CRUDAuthorization;
import org.example.crud.CRUDContact;
import org.example.crud.CRUDRelation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
public class Menu {

    private Menu()
    {
        throw new IllegalStateException("Utility class");
    }
    private static final Logger logger = LogManager.getLogger(Menu.class);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void menu() throws IOException, SQLException
    {
        String caseTable;
        do {
            logger.info("\n\t\t\t--------- SELECT TABLE --------" +
                    "\n\t1 - Authorization" +
                    "\n\t2 - Contact" +
                    "\n\t3 - Relation" +
                    "\n\tAnother - cancel operation" +
                    "\n\tEnter num:\n");

            caseTable = br.readLine();
            switch (caseTable) {
                case "1" ->
                        CRUDAuthorization.crudAuthorization();
                case "2" ->
                        CRUDContact.crudContact();
                case "3" ->
                        CRUDRelation.crudRelation();
                default -> {
                    logger.info("Exit");
                    return;
                }

            }
        } while (true);

    }
}
