/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

import advprogjava.Student;
import java.util.ArrayList;
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
     * Test of sortStudents method, of class AdvProgJava.
     */
    @Test
    public void testSortStudents1() {
        System.out.println("sortStudents");
        AdvProgJava instance = new AdvProgJava();
        ArrayList Students = new ArrayList<Student>();
        advprogjava.Student a = new advprogjava.Student(7, "Harry", "Mann", "Kinesiology");
        advprogjava.Student b = new advprogjava.Student(10, "Manfred", "Mann", "Music");
        advprogjava.Student c = new Student(15, "Scotty", "Mann", "Physics");
        
        ArrayList expResult = new ArrayList<Student>();
        expResult.add(a);
        expResult.add(b);
        expResult.add(c);
        ArrayList result = instance.sortStudents(Students, a, b, c);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of sortStudents method, of class AdvProgJava.
     */
    @Test
    public void testSortStudents2() {
        System.out.println("sortStudents");
        AdvProgJava instance = new AdvProgJava();
        ArrayList Students = new ArrayList<Student>();
        advprogjava.Student a = new advprogjava.Student(90, "Harry", "Mann", "Kinesiology");
        advprogjava.Student b = new advprogjava.Student(10, "Manfred", "Mann", "Music");
        advprogjava.Student c = new Student(45, "Scotty", "Mann", "Physics");
        
        ArrayList expResult = new ArrayList<Student>();
        expResult.add(b);
        expResult.add(c);
        expResult.add(a);
        ArrayList result = instance.sortStudents(Students, a, b, c);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of sortStudents method, of class AdvProgJava.
     */
    @Test
    public void testSortStudents3() {
        System.out.println("sortStudents");
        AdvProgJava instance = new AdvProgJava();
        ArrayList Students = new ArrayList<Student>();
        advprogjava.Student a = new advprogjava.Student(40, "Harry", "Mann", "Kinesiology");
        advprogjava.Student b = new advprogjava.Student(20, "Manfred", "Mann", "Music");
        advprogjava.Student c = new Student(10, "Scotty", "Mann", "Physics");
        
        ArrayList expResult = new ArrayList<Student>();
        expResult.add(c);
        expResult.add(b);
        expResult.add(a);
        ArrayList result = instance.sortStudents(Students, a, b, c);
        assertEquals(expResult, result);
        
    }
    
    
}
