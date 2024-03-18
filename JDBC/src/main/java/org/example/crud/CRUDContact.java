package org.example.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DBFunctions;
import org.example.data.Constants;
import org.example.data.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDContact {
    private CRUDContact() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LogManager.getLogger(CRUDContact.class);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void crudContact() throws IOException, SQLException {
        String caseTable;
        do {
            logger.info("\t\t\t--------- SELECT OPERATION --------\n" +
                    "\t1 - Create\n" +
                    "\t2 - Read\n" +
                    "\t3 - Update\n" +
                    "\t4 - Delete\n" +
                    "\t5 - Read contact by id\n" +
                    "\tAnother - cancel\n" +
                    "\tEnter num:");
            caseTable = br.readLine();
            switch (caseTable) {
                case "1" -> createContact();
                case "2" -> getAllContact();
                case "3" -> updateContact();
                case "4" -> deleteContact();
                case "5" ->
                {
                    logger.info("Enter id:");
                    Long id = Long.parseLong(br.readLine());
                    Contact conn =dbGetContactByID(id);
                    if(conn!=null)
                    showContact(conn);
                    else
                        logger.error("contact doesn't exist");
                }
                default -> {
                    logger.info("Exit");
                    return;
                }
            }
        } while (true);
    }
    public static void createContact() throws IOException, SQLException{
        // Ваша реализация метода
        logger.info("Enter user_id:");
        String userId = br.readLine();
        logger.info("Enter contact_type:");
        String contactType = br.readLine();
        logger.info("Enter info");
        String info = br.readLine();
        Contact co = new Contact(0L, Long.parseLong(userId), Long.parseLong(contactType), info);
        int status = dbSaveContact(co);

        if (status == 1)
            logger.info("Contact saved successfully");
        else
            logger.info("ERROR while saving user");
        logger.info("\n");
    }

    public static void getAllContact() {
        List<Contact> list = dbGetAllContacts();
        for (Contact co : list) {
            showContact(co);
        }
    }

    public static void updateContact() throws IOException,SQLException{
        // Ваша реализация метода
        logger.info("Contact ID: ");
        Long id = Long.valueOf(br.readLine());
        logger.info("New info: ");
        String newInfo = br.readLine();
        logger.info("New user_id: ");
        String newUserId = br.readLine();
        logger.info("New contact_type: ");
        String newType = br.readLine();
        Contact co = dbGetContactByID(id);
        co.setInfo(newInfo);
        co.setContactType(Long.parseLong(newType));
        co.setUserId(Long.parseLong(newUserId));
        int status = dbUpdateContact(co);
        if (status == 1) logger.info("Contact updated successfully");
        else
            logger.info("ERROR while updating user");
    }

    public static void deleteContact() throws IOException {

        logger.info("Contact ID: ");
        Long id = Long.valueOf(br.readLine());
        int status = dbDeleteContact(id);
        if (status == 1) logger.info("Contact deleted successfully");
        else
            logger.info("ERROR while deleting contact");
    }

    public static List<Contact> dbGetAllContacts() {

        List<Contact> contacts = new ArrayList<>();
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM contact");

            while (rs.next()) {
                Contact co = new Contact(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("contact_type"),
                        rs.getString("info")
                );
                contacts.add(co);
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
        return contacts;
    }

    public static void showContact(Contact in)
    {
        logger.info(in.toString());
    }
    public static Contact dbGetContactByID(Long id) {

        Contact co = null;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB("PFP", "krimlad", "krilop");
            ps = conn.prepareStatement("SELECT * FROM contact WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                co = new Contact(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("contact_type"),
                        rs.getString("info"));
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
        return co;
    }

    public static int dbSaveContact(Contact in) throws SQLException {

        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("INSERT INTO contact(user_id, contact_type, info) VALUES(?, ?, ?)");
            ps.setLong(1, in.getUserId());
            ps.setLong(2, in.getContactType());
            ps.setString(3, in.getInfo());
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

    public static int dbUpdateContact(Contact in) throws SQLException {
        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("UPDATE contact SET info=?, user_id=?, contact_type=? WHERE id=?");
            ps.setString(1, in.getInfo());
            ps.setLong(2,in.getUserId());
            ps.setLong(3,in.getContactType());
            ps.setLong(4, in.getId());
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

    public static int dbDeleteContact(Long userID) {
        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("DELETE FROM contact where id=?");
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