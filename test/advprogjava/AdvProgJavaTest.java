/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

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
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of addOne method, of class AdvProgJava.
     */
    @Test
    public void testAddOne1() {
        System.out.println("addOne");
        int[] inNums = new int[]{1 ,2, 3};
        AdvProgJava instance = new AdvProgJava();
        instance.addOne(inNums);
        int[] expected = new int[]{2, 3, 4};
        assertEquals(expected[0], inNums[0]);
        assertEquals(expected[1], inNums[1]);
        assertEquals(expected[2], inNums[2]);
    }
    
    @Test
    public void testAddOne2() {
        System.out.println("addOne");
        int[] inNums = new int[]{1, 2, 3, 4, 5};
        AdvProgJava instance = new AdvProgJava();
        instance.addOne(inNums);
        int[] expected = new int[]{2, 3, 4, 5, 6};
        assertEquals(expected[0], inNums[0]);
        assertEquals(expected[1], inNums[1]);
        assertEquals(expected[2], inNums[2]);
        assertEquals(expected[3], inNums[3]);
        assertEquals(expected[4], inNums[4]);
    }
    
    @Test
    public void testAddOne3() {
        System.out.println("addOne");
        int[] inNums = new int[]{3, 5, 6, 9, 13};
        AdvProgJava instance = new AdvProgJava();
        instance.addOne(inNums);
        int[] expected = new int[]{4, 6, 7, 10, 14};
        assertEquals(expected[0], inNums[0]);
        assertEquals(expected[1], inNums[1]);
        assertEquals(expected[2], inNums[2]);
        assertEquals(expected[3], inNums[3]);
        assertEquals(expected[4], inNums[4]);
    }
    
    

   
}
