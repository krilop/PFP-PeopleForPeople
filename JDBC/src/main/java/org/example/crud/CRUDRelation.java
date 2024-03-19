package org.example.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.DBFunctions;
import org.example.data.Constants;
import org.example.data.Relation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDRelation {
    private CRUDRelation() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LogManager.getLogger(CRUDRelation.class);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void crudRelation() throws IOException, SQLException {
        String caseTable;
        do {
            logger.info("\t\t\t--------- SELECT OPERATION --------\n" +
                    "\t1 - Create\n" +
                    "\t2 - Read\n" +
                    "\t3 - Update\n" +
                    "\t4 - Delete\n" +
                    "\t5 - Read relation by id\n" +
                    "\tAnother - cancel\n" +
                    "\tEnter num:");
            caseTable = br.readLine();
            switch (caseTable) {
                case "1" -> createRelation();
                case "2" -> getAllRelations();
                case "3" -> updateRelation();
                case "4" -> deleteRelation();
                case "5" -> {
                    logger.info("Enter id:");
                    Long id = Long.parseLong(br.readLine());
                    Relation rel = dbGetRelationByID(id);
                    if (rel != null)
                        showRelation(rel);
                    else
                        logger.error("Relation doesn't exist");
                }
                default -> {
                    logger.info("Exit");
                    return;
                }
            }
        } while (true);
    }

    public static void createRelation() throws IOException, SQLException {
        // Ваша реализация метода
        logger.info("Enter user_id:");
        String userId = br.readLine();
        logger.info("Enter contact_type:");
        String friendId = br.readLine();
        logger.info("Enter relation_type:");
        String relationType = br.readLine();
        Relation rel = new Relation(0L, Long.parseLong(userId), Long.parseLong(friendId), Long.parseLong(relationType));
        int status = dbSaveRelation(rel);

        if (status == 1)
            logger.info("Relation saved successfully");
        else
            logger.info("ERROR while saving relation");
        logger.info("\n");
    }

    public static void getAllRelations() {
        List<Relation> list = dbGetAllRelation();
        for (Relation rel : list) {
            showRelation(rel);
        }
    }

    public static void updateRelation() throws IOException, SQLException {
        // Ваша реализация метода
        logger.info("Relation ID: ");
        Long id = Long.valueOf(br.readLine());
        logger.info("Enter user_id:");
        String userId = br.readLine();
        logger.info("Enter contact_type:");
        String friendId = br.readLine();
        logger.info("Enter relation_type:");
        String relationType = br.readLine();
        Relation rel = dbGetRelationByID(id);
        rel.setUserId(Long.parseLong(userId));
        rel.setFriendId(Long.parseLong(friendId));
        rel.setRelationType(Long.parseLong(relationType));
        int status = dbUpdateRelation(rel);
        if (status == 1) logger.info("Relation updated successfully");
        else
            logger.info("ERROR while updating relation");
    }

    public static void deleteRelation() throws IOException {

        logger.info("Relation ID: ");
        Long id = Long.valueOf(br.readLine());
        int status = dbDeleteRelation(id);
        if (status == 1) logger.info("Relation deleted successfully");
        else
            logger.info("ERROR while deleting Relation");
    }

    public static List<Relation> dbGetAllRelation() {

        List<Relation> relations = new ArrayList<>();
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM relation");

            while (rs.next()) {
                Relation rel = new Relation(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("friend_id"),
                        rs.getLong("relation_type")
                );
                relations.add(rel);
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
        return relations;
    }

    public static void showRelation(Relation in) {
        if (in != null) {
            String str = in.toString();
            logger.info(str);
        }
    }

    public static Relation dbGetRelationByID(Long id) {

        Relation rel = null;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB("PFP", "krimlad", "krilop");
            ps = conn.prepareStatement("SELECT * FROM relation WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                rel = new Relation(rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("friend_id"),
                        rs.getLong("relation_type"));
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
        return rel;
    }

    public static int dbSaveRelation(Relation relation) throws SQLException {
        // Подключение к базе данных
        DBFunctions func = new DBFunctions();
        try {
            Connection connection = func.connectToDB("PFP", "krimlad", "krilop");
            PreparedStatement checkStatement = connection.prepareStatement("SELECT relation_type FROM relation WHERE user_id = ? AND friend_id = ?");
            checkStatement.setLong(1, relation.getFriendId());
            checkStatement.setLong(2, relation.getUserId());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                // Если запрос уже существует, обновляем его тип на 2
                String updateQuery = "UPDATE relation SET relation_type = 2 WHERE user_id = ? AND friend_id = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setLong(1, relation.getFriendId());
                    updateStatement.setLong(2, relation.getUserId());
                    return updateStatement.executeUpdate();
                }
            } else {
                // Если запрос не существует, добавляем новую запись
                String insertQuery = "INSERT INTO relation (user_id, friend_id, relation_type) VALUES (?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setLong(1, relation.getUserId());
                    insertStatement.setLong(2, relation.getFriendId());
                    insertStatement.setLong(3, relation.getRelationType());
                    return insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return -1;
    }


    public static int dbUpdateRelation(Relation in) throws SQLException {
        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("UPDATE relation SET user_id=?, friend_id=?, relation_type=? WHERE id=?");
            ps.setLong(1, in.getUserId());
            ps.setLong(2, in.getFriendId());
            ps.setLong(3, in.getRelationType());
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

    public static int dbDeleteRelation(Long userID) {
        int status = 0;
        DBFunctions db = new DBFunctions();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = db.connectToDB(Constants.DATABASE_NAME, Constants.USERNAME, Constants.PASSWORD);
            ps = conn.prepareStatement("DELETE FROM relation where id=?");
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