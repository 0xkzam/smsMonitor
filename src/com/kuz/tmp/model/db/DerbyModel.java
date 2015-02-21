package com.kuz.tmp.model.db;

import com.kuz.tmp.model.bean.Message;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Modeling the Derby database/Message Data Access Object
 *
 * @author Kasun Amarasena
 */
public final class DerbyModel implements Model {

    private final Connection connection = new DerbyDBConnection().getConnection();
    private final Logger logger = Logger.getLogger(DerbyModel.class);

    //PHONENO VARCHAR(12),CONTENTS VARCHR(220), SENT_TIMESTAMP TIMESTAMP, RECEIVED_TIMESTAMP TIMESTAMP
    private final static String INSERT_MESSAGE = "insert into MESSAGE values (?, ?, ?, ?)";
    private final static String GET_MESSAGES = "select * from MESSAGE order by SENT_TIMESTAMP desc";
    private final static String GET_MIN_DATE = "select min(SENT_TIMESTAMP) from MESSAGE";
    private final static String GET_MAX_DATE = "select max(SENT_TIMESTAMP) from MESSAGE";
    private final static String GET_ROW_COUNT = "select count(PHONENO) from MESSAGE";
    private final static String GET_MESSAGES_BY_DATE_RANGE = "select * from MESSAGES where SENT_TIMESTAMP >= ? and SENT_TIMESTAMP <= ? order by SENT_TIMESTAMP desc";
    private final static String DELETE_MESSAGE = "delete from MESSAGE where RECEIVED_TIMESTAMP = ?";

    @Override
    public boolean insert(Message message) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(INSERT_MESSAGE);) {
            connection.setAutoCommit(false);
            st.setString(1, message.getNumber());
            st.setString(2, message.getContents());
            st.setTimestamp(3, new Timestamp(message.getSentDate().getTime()));
            st.setTimestamp(4, message.getStamp());
            st.execute();
            connection.commit();
        } catch (SQLException ex) {
            success = false;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                logger.error("Error inserting message", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                logger.warn("Error setting auto commit", ex);
            }
        }
        return success;
    }

    @Override
    public int[] insert(List<Message> messages) {
        int[] info = null;
        try (PreparedStatement st = connection.prepareStatement(INSERT_MESSAGE);) {
            connection.setAutoCommit(false);
            for (Message message : messages) {
                st.setString(1, message.getNumber());
                st.setString(2, message.getContents());
                st.setTimestamp(3, new Timestamp(message.getSentDate().getTime()));
                st.setTimestamp(4, message.getStamp());
                st.addBatch();
            }
            info = st.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            logger.error("Error inserting message", ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                logger.error("Error rolling back", ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                logger.error("Error setting auto commit", ex);
            }
        }
        return info;
    }

    @Override
    public boolean delete(Timestamp receivedStamp) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(DELETE_MESSAGE)) {
            connection.setAutoCommit(false);
            st.setTimestamp(1, receivedStamp);
            st.execute();
            connection.commit();
        } catch (SQLException ex) {
            success = false;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                logger.error("Error rolling back", ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                logger.error("Error setting auto commit", ex);
            }
        }
        return success;
    }

    @Override
    public int[] delete(List<Timestamp> receivedStamps) {
        int[] info = null;
        try (PreparedStatement st = connection.prepareStatement(DELETE_MESSAGE)) {
            connection.setAutoCommit(false);
            for (Timestamp stamp : receivedStamps) {
                st.setTimestamp(1, stamp);
                st.addBatch();
            }
            info = st.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                logger.error("Error rolling back", ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                logger.error("Error setting auto commit", ex);
            }
        }
        return info;
    }

    @Override
    public List<Message> getMessagesFromRange(Date startDate, Date endDate) {
        List<Message> messages = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(GET_MESSAGES_BY_DATE_RANGE)) {
            connection.setAutoCommit(false);
            st.setTimestamp(1, new Timestamp(startDate.getTime()));
            st.setTimestamp(2, new Timestamp(endDate.getTime()));
            rs = st.executeQuery();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                messages = null;
                logger.error("Error rolling back", ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                logger.error("Error setting auto commit", ex);
            }
        }
        if (rs != null) {
            try {
                while (rs.next()) {
                    Message m = new Message(rs.getString(1), rs.getString(2), new Date(rs.getTimestamp(3).getTime()), rs.getTimestamp(4));
                    messages.add(m);
                }
            } catch (SQLException ex) {
                logger.error("Error reading ResultSet", ex);
            }
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesFromRange(int startRow, int endRow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getMinDate() {
        Date date = null;
        try (PreparedStatement st = connection.prepareStatement(GET_MIN_DATE)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDate(1);
            }
        } catch (SQLException ex) {
            logger.error("Error reading ResultSet-getMinDate", ex);
        }
        return date;
    }

    @Override
    public Date getMaxDate() {
        Date date = null;
        try (PreparedStatement st = connection.prepareStatement(GET_MAX_DATE)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDate(1);
            }
        } catch (SQLException ex) {
            logger.error("Error reading ResultSet-getMaxDate", ex);
        }
        return date;
    }

}
