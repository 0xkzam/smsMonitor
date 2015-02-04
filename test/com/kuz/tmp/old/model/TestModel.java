/*
 * Copyright (C) 2015 Kasun Amarasena
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

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class TestModel {
    
    private static Logger logger;

    public TestModel() {
    }
    
    @BeforeClass
    public  static void beforeClass(){
        logger = Logger.getLogger(TestModel.class);
    }

    @Test
    public void test() {       
        
        logger.info("testing logger");
    }
}
