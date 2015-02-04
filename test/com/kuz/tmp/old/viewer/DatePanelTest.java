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
package com.kuz.tmp.old.viewer;

import com.kuz.tmp.old.model.Query;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class DatePanelTest {

    public DatePanelTest() {
    }

    /**
     * Test of setMinDate method, of class DatePanel.
     */
    @Test
    public void testSetMinDate() {
        System.out.println("Testing setMinDate()");
        try {
            Query.getCurrentDate();
        } catch (SQLException ex) {
            assertFalse(ex.toString(),true);
        }

    }

}
