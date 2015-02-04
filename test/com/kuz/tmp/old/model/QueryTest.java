/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kuz.tmp.old.model;

import com.kuz.tmp.old.model.Query;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class QueryTest {

    public QueryTest() {
    }

    /**
     * Test of deleteRecord method, of class Query.
     */
    @Test
    public void testDeleteRecord() throws Exception {
        System.out.println("deleteRecord");
        ResultSet select = Query.select();
        while (select.next()) {
            String num = select.getString("phone_no");
            Date date = select.getDate("dateVar");
            Time time = select.getTime("timeVar");
            Timestamp stamp = select.getTimestamp("stamp");

            System.out.println("phone:"+ num+"  date: "+date+ "  time: "+time+"  stamp: "+stamp);
            //Query.deleteRecord(num, date, time);

        }
//        ResultSet r = Query.select();
//        if(r.next()){
//            Assert.assertFalse("Delete statement error", true);
//        }else{
//            System.out.println("Success");
//        }

    }

}
