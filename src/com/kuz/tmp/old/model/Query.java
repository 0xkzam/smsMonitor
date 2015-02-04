
package com.kuz.tmp.old.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * This class provides static methods for database interactions
 *
 * @author Kasun Amarasena
 */

@Deprecated
public class Query {

    private final static String INSERT = "insert into info values (?, ?, ?, ?, current_timestamp)";
    private final static String EMPTY_LISTENER = "delete from listener";
    private final static String GET_LISTENER = "select * from listener";
    private final static String GET_INFO = "select * from info order by dateVar desc,timeVar desc";
    private final static String MIN_DATE = "select min(dateVar) from info";
    private final static String MAX_DATE = "select max(dateVar) from info";
    private final static String ROW_COUNT = "select count(phone_no) from info";
    private final static String GET_INFO_BY_DATE_RANGE = "select * from info where dateVar >= ? and dateVar <= ? order by dateVar desc,timeVar desc";
    private final static String DELETE_RECORD = "delete from info where stamp = ?";

    private static final Connection con = DBConnection.getInstance();

//    // Frequently used statements
//    private static PreparedStatement st_insert, st_emptyListener, st_selectFromListner, st_getInfo;
//
//    //Unsure about this modification using static block
//    //
//    static {
//        try {
//            st_insert = con.prepareStatement(INSERT);
//            st_emptyListener = con.prepareStatement(EMPTY_LISTENER);
//            st_selectFromListner = con.prepareStatement(GET_LISTENER);
//            st_getInfo = con.prepareStatement(GET_INFO);
//        } catch (SQLException ex) {
//            try {
//                con.close();
//            } catch (SQLException ex1) {                
//            }
//        }
//    }

    /**
     * Execute INSERT statement for incoming text messages
     *
     * @param num String phone number/Special ID
     * @param date java.sql.Date object
     * @param time java.sql.Time object
     * @param msg String message
     * @throws java.sql.SQLException
     */
    public static void insert(String num, Date date, Time time, String msg) throws SQLException {
        try (PreparedStatement st_insert = con.prepareStatement(INSERT)) {
            st_insert.setString(1, num);
            st_insert.setDate(2, date);
            st_insert.setTime(3, time);
            st_insert.setString(4, msg);
            st_insert.execute();
        }
    }

    /**
     * Execute batch INSERT a prepared statement
     *
     * @param numList List<String> of phone numbers
     * @param dateList List<java.sql.Date> of dates
     * @param timeList List<java.sql.Time> of message received time
     * @param msgList List<String> of text messages
     * @throws java.sql.SQLException
     */
    public static void insert(List<String> numList, List<Date> dateList, List<Time> timeList, List<String> msgList) throws SQLException {
        try (PreparedStatement st_insert = con.prepareStatement(INSERT)) {
            for (int i = 0; i < numList.size(); i++) {
                st_insert.setString(1, numList.get(i));
                st_insert.setDate(2, dateList.get(i));
                st_insert.setTime(3, timeList.get(i));
                st_insert.setString(4, msgList.get(i));
                st_insert.addBatch();
            }
            st_insert.executeBatch();
        }
    }

    /**
     * Deletes all records from the table LISTENER. This method is part of the
     * database change listener.
     *
     * @throws SQLException
     */
    public static void emptyListenerTable() throws SQLException {
        PreparedStatement st_emptyListener = con.prepareStatement(EMPTY_LISTENER);
        st_emptyListener.execute();
    }

    /**
     * Execute SELECT statement - table LISTENER
     *
     * @return ResultSet
     * @throws java.sql.SQLException
     */
    public static ResultSet selectFromListener() throws SQLException {
        PreparedStatement st_selectFromListner = con.prepareStatement(GET_LISTENER);
        st_selectFromListner.executeQuery();
        return st_selectFromListner.getResultSet();
    }

    /**
     * SELECT all from - table INFO
     *
     * @return ResultSet
     * @throws java.sql.SQLException
     */
    public static ResultSet select() throws SQLException {
        PreparedStatement st_getInfo = con.prepareStatement(GET_INFO);
        st_getInfo.executeQuery();
        return st_getInfo.getResultSet();
    }

    /**
     *
     * BUGGY Code - Use the other method instead Delete the specified record
     * from the database.
     *
     * @param num String phone number
     * @param date java.sql.Date
     * @param time java.sql.Time
     * @throws java.sql.SQLException
     */
    public static void deleteRecord(String num, Date date, Time time) throws SQLException {
        try (PreparedStatement st = con.prepareStatement("select * from info where phone_no = ? and dateVar = ? and timeVar = ?")) {
            st.setString(1, num);
            st.setDate(2, date);
            st.setTime(3, time);
            st.execute();
        }
    }

    /**
     * Delete the specified record from the database.
     *
     * @param stamp java.sql.TimeStamp
     * @throws SQLException
     */
    public static void deleteRecord(Timestamp stamp) throws SQLException {
        try (PreparedStatement st = con.prepareStatement(DELETE_RECORD)) {
            st.setTimestamp(1, stamp);
            st.execute();
        }
    }

    /**
     * @return java.sql.Date of the earliest date of text message received
     * @throws SQLException
     */
    public static Date getMinDate() throws SQLException {
        Date date = null;
        try (PreparedStatement st = con.prepareStatement(MIN_DATE)) {
            st.executeQuery();
            ResultSet r = st.getResultSet();
            if (r.next()) {
                date = r.getDate(1);
            }
        }
        return date;
    }

    /**
     * @return java.sql.Date of the most recent date of text message received
     * @throws SQLException
     */
    public static Date getCurrentDate() throws SQLException {
        Date date;
        try (PreparedStatement st = con.prepareStatement(MAX_DATE)) {
            st.executeQuery();
            ResultSet r = st.getResultSet();
            date = null;
            if (r.next()) {
                date = r.getDate(1);
            }
        }
        return date;
    }

    /**
     * @param from java.sql.Date
     * @param to java.sql.Date
     * @return ResultSet containing all text message date between specified
     * dates
     * @throws SQLException
     */
    public static ResultSet select(Date from, Date to) throws SQLException {
        PreparedStatement st = con.prepareStatement(GET_INFO_BY_DATE_RANGE);
        st.setDate(1, from);
        st.setDate(2, to);
        ResultSet executeQuery = st.executeQuery();
        return executeQuery;
    }

    /**
     * @return int of the number of records in the info table
     */
    public static int rowCount() {
        try {
            PreparedStatement st = con.prepareStatement(ROW_COUNT);
            ResultSet r = st.executeQuery();
            if (r.next()) {
                return r.getInt(1);
            }
        } catch (SQLException ex) {
            
        }

        return 0;
    }

    //##########################################################################
    //####### Following methods are only used in the development process #######
    //##########################################################################
    /**
     * Initial Run- database creation
     *
     * @param connection java.sql.Connection
     * @throws SQLException
     */
    static void createSMSDB(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        Statement st = connection.createStatement();
        st.addBatch("create table info (phone_no varchar(40),dateVar DATE,timeVar TIME, message varchar(220), stamp timestamp default current_timestamp )");
        st.addBatch("create table listener(id BOOLEAN)");
        st.addBatch("create trigger trig after insert on info "
                + "for each row "
                + "insert into listener values (true)");
        st.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);

    }

    /**
     * Drop a table from the database
     *
     * @throws SQLException
     */
    static void drop(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("drop table info")) {
            st.execute();
        }
    }

    /**
     * Execute CREATE TABLE statement with a prepared statement.
     *
     * @throws SQLException
     */
    static void createTable(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("create table info (phone_no varchar(40),"
                + "dateVar DATE,timeVar TIME, message varchar(220))")) {
            st.execute();
        }
    }

    static void createTable2(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("create table listener(id BOOLEAN)")) {
            st.execute();
        }
    }

    static void createTrigger(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("create trigger trig after insert on info "
                + "for each row "
                + "insert into listener values (true)")) {
            st.execute();
        }
    }

    static void addColumn(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("alter table info add column stamp timestamp default current_timestamp")) {
            st.execute();
        }
    }

    static void delete(Connection connection, String tableName) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement("delete from " + tableName)) {
            st.execute();
        }
    }

}
