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
 * Modeling the Derby database-Message Data Access Object
 *
 * @author Kasun Amarasena
 */
public final class DerbyMessageDAO implements MessageDAO {

    private final Connection connection = new DerbyDBConnection().getConnection();
    private final Logger logger = Logger.getLogger(DerbyMessageDAO.class);


    @Override
    public boolean insert(Message message) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(INSERT_MESSAGE);) {
            connection.setAutoCommit(false);
            st.setString(1, message.getNumber());
            st.setString(2, message.getContents());
            st.setTimestamp(3, new Timestamp(message.getSentDate().getTime()));
            st.setTimestamp(4, new Timestamp(message.getReceivedDate().getTime()));
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
    public boolean insert(List<Message> messages) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(INSERT_MESSAGE);) {
            connection.setAutoCommit(false);
            for (Message message : messages) {
                st.setString(1, message.getNumber());
                st.setString(2, message.getContents());
                st.setTimestamp(3, new Timestamp(message.getSentDate().getTime()));
                st.setTimestamp(4, new Timestamp(message.getReceivedDate().getTime()));
                st.addBatch();
            }
            st.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            success = false;
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
        return success;
    }

    @Override
    public boolean delete(Date receivedDate) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(DELETE_MESSAGE)) {
            connection.setAutoCommit(false);
            st.setTimestamp(1, new Timestamp(receivedDate.getTime()));
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
    public boolean delete(List<Date> receivedDates) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(DELETE_MESSAGE)) {
            connection.setAutoCommit(false);
            for (Date date : receivedDates) {
                st.setTimestamp(1, new Timestamp(date.getTime()));
                st.addBatch();
            }
            st.executeBatch();
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
