/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.sql.SQLException;

/**
 *
 * @author Kasun Amarasena
 */
public class TestDB {

    public static void main(String[] args) throws SQLException {
        testSelect();
    }

    static void testSelect() throws SQLException {
        Query.delete();
    }
}
