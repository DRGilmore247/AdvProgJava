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
     * Test of daysBelow method, of class AdvProgJava.
     */
    @Test
    public void testDaysBelow1() {
        System.out.println("daysBelow");
        String[] args = new String[] {"42", "-2", "-7", "10", "14"};
        AdvProgJava instance = new AdvProgJava();
        int expResult = 2;
        int result = instance.daysBelow(args);
        assertEquals(expResult, result); 
    }
    
    @Test
    public void testDaysBelow2() {
        System.out.println("daysBelow");
        String[] args = new String[] {"-3", "-5", "-11", "10", "0"};
        AdvProgJava instance = new AdvProgJava();
        int expResult = 3;
        int result = instance.daysBelow(args);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDaysBelow3() {
        System.out.println("daysBelow");
        String[] args = new String[] {"11", "17", "18", "9", "2"};
        AdvProgJava instance = new AdvProgJava();
        int expResult = 0;
        int result = instance.daysBelow(args);
        assertEquals(expResult, result);
    }
        
    
}
    

