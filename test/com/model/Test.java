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
package com.model;

import java.io.Closeable;

/**
 *
 * @author Kasun Amarasena
 */
public class Test {

    public static void main(String[] args) {

        try (A a = new A()) {
            
            a.test(0);
            
        } catch (Exception ex) {
            System.out.println("catch:"+ex);
        }
    }

}




class A implements Closeable {

    A() {
        System.out.println("A constructor");
    }

    public void test(int i) throws Exception {
        if (i == 0) {
            throw new Exception("i == 0");
        }
        System.out.println("testing try-with-resources");

    }

    @Override
    public void close() {
        System.out.println("A Closing......");
    }
}
