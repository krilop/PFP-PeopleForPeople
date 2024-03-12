package org.example.crud;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CRUDContact {
    private CRUDContact() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LogManager.getLogger(CRUDAuthorization.class);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void crudContact() throws IOException, SQLException {
        String caseTable;
        do {
            logger.info("%n\t\t\t--------- SELECT OPERATION --------\n" +
                    "\t1 - Create\n" +
                    "\t2 - Read\n" +
                    "\t3 - Update\n" +
                    "\t4 - Delete\n" +
                    "\tAnother - cancel\n" +
                    "\tEnter num:");
            caseTable = br.readLine();
            switch (caseTable) {
                case "1" -> createUser();
                case "2" -> getAllUsers();
                case "3" -> updateUser();
                case "4" -> deleteUser();
                default -> {
                    logger.info("Exit");
                    return;
                }
            }
        } while (true);
    }
    public static void createUser() {
        // Ваша реализация метода
    }

    public static void getAllUsers() {
        // Ваша реализация метода
    }

    public static void updateUser() {
        // Ваша реализация метода
    }

    public static void deleteUser() {
        // Ваша реализация метода
    }

}