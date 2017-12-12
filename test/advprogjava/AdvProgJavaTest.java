/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author David
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class AdvProgJavaTest {
    
    static AdvProgJava instance;
    
    public AdvProgJavaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new AdvProgJava();
        instance.reset();
        Connection connection = instance.getConnection();
        
        String sql = "insert or replace into backlog (Title, Genre, Length) values (?, ?, ?)";
        try
        {
            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, "darkest dungeon");
            ps.setString(2, "rpg");
            ps.setString(3, "long");
            ps.executeUpdate();
            
            PreparedStatement ps2
                    = connection.prepareStatement(sql);
            ps2.setString(1, "overwatch");
            ps2.setString(2, "multiplayer");
            ps2.setString(3, "multiplayer");
            ps2.executeUpdate();
        }
        catch (Exception ex)
        {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        
        
    }
    
    @After
    public void tearDown() {
        instance.closeConnnection();
    }

    /**
     * Test of parse method, of class AdvProgJava.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        String input = "add, monster hunter world";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 1;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse2() {
        System.out.println("parse");
        String input = "remove, monster hunter world, action";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 2;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse3() {
        System.out.println("parse");
        String input = "list, all";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 3;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse4() {
        System.out.println("parse");
        String input = "find, rpg";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 4;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse5() {
        System.out.println("parse");
        String input = "search";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 5;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse6() {
        System.out.println("parse");
        String input = "help";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 6;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse7() {
        System.out.println("parse");
        String input = "hello";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 7;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParse0() {
        System.out.println("parse");
        String input = "exit";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 0;
        int result = instance.parse(input);
        assertEquals(expResult, result);
    }

    /**
     * Test of sanitize method, of class AdvProgJava.
     */
    @Test
    public void testSanitize() {
        System.out.println("sanitize");
        String inGenre = "rpg";
        String inLength = "long";
        AdvProgJava instance = new AdvProgJava();
        boolean expResult = true;
        boolean result = instance.sanitize(inGenre, inLength);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSanitize2() {
        System.out.println("sanitize");
        String inGenre = "rpg";
        String inLength = "uberlong";
        AdvProgJava instance = new AdvProgJava();
        boolean expResult = false;
        boolean result = instance.sanitize(inGenre, inLength);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSanitize3() {
        System.out.println("sanitize");
        String inGenre = "horror";
        String inLength = "long";
        AdvProgJava instance = new AdvProgJava();
        boolean expResult = false;
        boolean result = instance.sanitize(inGenre, inLength);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSanitize4() {
        System.out.println("sanitize");
        String inGenre = "horror";
        String inLength = "uberlong";
        AdvProgJava instance = new AdvProgJava();
        boolean expResult = false;
        boolean result = instance.sanitize(inGenre, inLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertGame method, of class AdvProgJava.
     */
    @Test
    public void testInsertGame() {
        System.out.println("insertGame");
        String inTitle = "final fantasy xv";
        String inGenre = "rpg";
        String inLength = "long";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 1;
        int result = instance.insertGame(inTitle, inGenre, inLength);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsertGame2() {
        System.out.println("insertGame");
        String inTitle = "for honor";
        String inGenre = "multiplayer";
        String inLength = "multiplayer";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 1;
        int result = instance.insertGame(inTitle, inGenre, inLength);
        assertEquals(expResult, result);
    }

    /**
     * Test of findGames method, of class AdvProgJava.
     */
    @Test
    public void testFindGames() {
        System.out.println("findGames");
        String inGenre = "rpg";
        String inLength = "long";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 2;
        ResultSet result = instance.listGames();
        
        int rows = 0;
        try
        {
            while (result.next())
                {
                    rows++;
                }
        }
        catch (Exception ex)
        {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        assertEquals(expResult, rows);
    }
    
    @Test
    public void testFindGames2() {
        System.out.println("findGames");
        String inGenre = "multiplayer";
        String inLength = "multiplayer";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 2;
        ResultSet result = instance.listGames();
        
        int rows = 0;
        try
        {
        while (result.next())
                {
                    rows++;
                }
        }
        catch (Exception ex)
        {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        assertEquals(expResult, rows);
    }
    
    @Test
    public void testFindGames3() {
        System.out.println("findGames");
        String inGenre = "rpg";
        String inLength = "medium";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 0;
        ResultSet result = instance.listGames();
        
        int rows = 0;
        try
        {
        while (result.next())
                {
                    rows++;
                }
        }
        catch (Exception ex)
        {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        assertEquals(expResult, rows);
    }

    /**
     * Test of listGames method, of class AdvProgJava.
     */
    @Test
    public void testListGames() {
        System.out.println("listGames");
        AdvProgJava instance = new AdvProgJava();
        int expResult = 4;
        ResultSet result = instance.listGames();
        
        int rows = 0;
        try
        {
        while (result.next())
                {
                    rows++;
                }
        }
        catch (Exception ex)
        {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        assertEquals(expResult, rows);
    }

    /**
     * Test of removeGame method, of class AdvProgJava.
     */
    @Test
    public void testRemoveGame() {
        System.out.println("removeGame");
        String inTitle = "overwatch";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 1;
        int result = instance.removeGame(inTitle);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRemoveGame2() {
        System.out.println("removeGame");
        String inTitle = "paladins";
        AdvProgJava instance = new AdvProgJava();
        int expResult = 0;
        int result = instance.removeGame(inTitle);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchGame method, of class AdvProgJava.
     */
    @Test
    public void testSearchGame() {
        System.out.println("searchGame");
        String inTitle = "darkest dungeon";
        AdvProgJava instance = new AdvProgJava();
        boolean expResult = true;
        boolean result = instance.searchGame(inTitle);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSearchGame2() {
        System.out.println("searchGame");
        String inTitle = "lawbreakers";
        AdvProgJava instance = new AdvProgJava();
        boolean expResult = false;
        boolean result = instance.searchGame(inTitle);
        assertEquals(expResult, result);
    }
}