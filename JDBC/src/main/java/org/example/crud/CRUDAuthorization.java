package org.example.crud;


import com.google.common.hash.Hashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.data.Authorization;
import org.example.DBFunctions;
import org.example.data.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDAuthorization {


    private CRUDAuthorization() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LogManager.getLogger(CRUDAuthorization.class);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void crudAuthorization() throws IOException, SQLException {
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

    public static void createUser() throws IOException, SQLException {

        logger.info("Enter login:");
        String login = br.readLine();
        logger.info("Enter password:");
        String password = br.readLine();
        logger.info("Enter email");
        String email = br.readLine();
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        Authorization user = new Authorization(0L, login, sha256hex, email);
        int status = dbSaveUser(user);

        if (status == 1)
            logger.info("User saved successfully");
        else
            logger.info("ERROR while saving user");
        logger.info("\n");
    }

    public static void getAllUsers() {
        List<Authorization> users = dbGetAllUsers();
        for (Authorization user : users) {
            showUser(user);
        }

    }

    public static void showUser(Authorization user) {
        String string = user.toString();
        logger.info(string);
    }

    public static void updateUser() throws IOException, SQLException {

        logger.info("User ID: ");
        Long id = Long.valueOf(br.readLine());
        logger.info("New email: ");
        String newEmail = br.readLine();

        Authorization user = dbGetUserByID(id);
        user.setEmail(newEmail);
        int status = dbUpdateUser(user);
        if (status == 1) logger.info("User updated successfully");
        else
            logger.info("ERROR while updating user");
    }

    public static void deleteUser() throws IOException {
        logger.info("User ID: ");
        Long id = Long.valueOf(br.readLine());
        int status = dbDeleteUser(id);
        if (status == 1) logger.info("User deleted successfully");
        else
            logger.info("ERROR while deleting user");
    }

    public static List<Authorization> dbGetAllUsers() {

        List<Authorization> users = new ArrayList<>();
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM \"authorization\"");

            while (rs.next()) {
                Authorization user = new Authorization(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("hash_of_pass"),
                        rs.getString("login")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Обработка исключения при закрытии ResultSet
                    logger.error(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e);
                }

            }
        }
        return users;
    }

    public static Authorization dbGetUserByID(Long id) {

        Authorization user = null;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB("PFP", "krimlad", "krilop");
            ps = conn.prepareStatement("SELECT * FROM \"authorization\" WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new Authorization(rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("hash_of_pass"),
                        rs.getString("login"));

            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Обработка исключения при закрытии ResultSet
                    logger.error(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e);
                }

            }
        }
        return user;
    }

    public static int dbSaveUser(Authorization user) throws SQLException {

        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("INSERT INTO \"authorization\"(email, hash_of_pass, login) VALUES(?, ?, ?)");
            ps.setString(3, user.getEmail());
            ps.setString(2, user.getHashOfPass());
            ps.setString(1, user.getLogin());
            status = ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return status;
    }

    public static int dbUpdateUser(Authorization user) throws SQLException {
        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("UPDATE \"authorization\" SET email=? WHERE id=?");
            ps.setString(1, user.getEmail());
            ps.setLong(2, user.getId());
            status = ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return status;
    }

    public static int dbDeleteUser(Long userID) {
        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("DELETE FROM \"authorization\" where id=?");
            ps.setLong(1, userID);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return status;
    }
}


