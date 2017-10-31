/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class AdvProgJavaTest {
    
    AdvProgJava instance;
    
    public AdvProgJavaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        instance = new AdvProgJava();
        instance.reset();
        instance.insertPoint(1, 11);
        instance.insertPoint(2, 22);
        instance.insertPoint(3, 33);
    }
    
    @After
    public void tearDown() {
    }

   
    /**
     * Test of insertPoint method, of class AdvProgJava.
     */
    @Test
    public void testInsertPoint1() {
        System.out.println("insertPoint");
        int xval = 8;
        int yval = 4;
        int expResult = 1;
        int result = instance.insertPoint(xval, yval);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsertPoint2() {
        System.out.println("insertPoint");
        int xval = 4;
        int yval = 2;
        int expResult = 1;
        int result = instance.insertPoint(xval, yval);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsertPoint3() {
        System.out.println("insertPoint");
        int xval = 2;
        int yval = 1;
        int expResult = 1;
        int result = instance.insertPoint(xval, yval);
        assertEquals(expResult, result);
    }

    /**
     * Test of getYVal method, of class AdvProgJava.
     */
    @Test
    public void testGetYVal1() {
        System.out.println("getYVal");
        int xval = 1;
        String expResult = "11";
        String result = instance.getYVal(xval);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetYVal2() {
        System.out.println("getYVal");
        int xval = 3;
        String expResult = "33";
        String result = instance.getYVal(xval);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetYVal3() {
        System.out.println("getYVal");
        int xval = 2;
        String expResult = "22";
        String result = instance.getYVal(xval);
        assertEquals(expResult, result);
    }

    
    
}
