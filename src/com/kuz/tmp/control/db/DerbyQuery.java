package com.kuz.tmp.control.db;

import com.kuz.tmp.model.Message;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Querying the Derby database
 *
 * @author Kasun Amarasena
 */
public final class DerbyQuery implements Query {

    private final Connection connection = new DerbyDBConnection().getConnection();
    private final Logger logger = Logger.getLogger(DerbyQuery.class);

    private final static String INSERT_MESSAGE = "insert into MESSAGE values (?, ?, ?, ?, ?)";
    private final static String GET_MESSAGES = "select * from MESSAGE order by DATEVAR desc,TIMEVAR desc";
    private final static String GET_MIN_DATE = "select min(DATEVAR) from MESSAGE";
    private final static String GET_MAX_DATE = "select max(DATEVAR) from MESSAGE";
    private final static String GET_ROW_COUNT = "select count(PHONENO) from MESSAGE";
    private final static String GET_MESSAGES_BY_DATE_RANGE = "select * from MESSAGES where DATEVAR >= ? and DATAVAR <= ? order by DATEVAR desc,TIMEVAR desc";
    private final static String DELETE_MESSAGE= "delete from MESSAGE where STAMP = ?";

    @Override
    public boolean insert(Message message) {
        boolean success = true;
        try (PreparedStatement st = connection.prepareStatement(INSERT_MESSAGE);) {
            connection.setAutoCommit(false);
            st.setString(1, message.getNumber());
            st.setDate(2, new Date(message.getDate().getTime()));
            st.setTime(3, new Time(message.getDate().getTime()));
            st.setString(4, message.getMessage());
            st.setTimestamp(5, message.getStamp());
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
                st.setDate(2, new Date(message.getDate().getTime()));
                st.setTime(3, new Time(message.getDate().getTime()));
                st.setString(4, message.getMessage());
                st.setTimestamp(5, message.getStamp());
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
    public boolean delete(Timestamp stamp) {
        boolean success = true;
        try(PreparedStatement st = connection.prepareStatement(DELETE_MESSAGE)){
            connection.setAutoCommit(false);
            st.setTimestamp(1, stamp);
            st.execute();
            connection.commit();
        } catch (SQLException ex) {
            success = false;
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                logger.error("Error rolling back", ex1);
            }
        }finally{
            try {
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                logger.error("Error setting auto commit", ex);
            }
        }
        return success;
    }

    @Override
    public int[] delete(List<Timestamp> stamps) {
           int[] info = null;
           
           return info;
    }

    @Override
    public List<Message> selectFromRange(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Message> selectFromRange(int startRow, int endRow) {
        return null;
    }

}
