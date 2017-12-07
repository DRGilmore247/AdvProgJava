/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
public class AdvProgJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AdvProgJava().run();
    }
    
    private Connection connection = null;

    
    public Connection getConnection() {
        if (connection == null) {
            synchronized (this) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                    } catch (SQLException ex) {
                        Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
//        try
//        {
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            statement.executeUpdate("drop table if exists backlog");
//            statement.executeUpdate("create table backlog (Title string primary key, Genre string, Length string)");
//        }
//        catch (SQLException ex)
//        {
//            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return connection;
}
    
    void reset() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists backlog");
            statement.executeUpdate("create table backlog (Title string primary key, Genre string, Length string)");
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
    int parse(String input)
    {
        String[] tokens  = input.toLowerCase().split(",");
        
        for (int i = 0; i < tokens.length; i++)
            tokens[i] = tokens[i].trim();
        
        if (tokens[0].equals("add"))
        {
            if (tokens.length != 4)
            {
                System.out.println("Incorrect number of arguments for Add.");
                return 1;
            }
            
            //Sanitize
            if (sanitize(tokens[2], tokens[3]))
            {
                try
                {
                    int result = insertGame(tokens[1], tokens[2], tokens[3]);
                    
                    if (result == 1)
                    {
                        System.out.println(tokens[1] + " was successfully added.");
                    }
                    else
                    {
                        System.out.println("Unable to add " + tokens[1]);
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("Something broke in Add.");
                }
            }
            else
            {
                System.out.println("Incorrect genre or length.");
            }
            
            return 1;
        }
        else if (tokens[0].equals("remove"))
        {
            if (tokens.length != 2)
            {
                System.out.println("Incorrect number of arguments for Remove.");
                return 1;
            }
            
            try
            {
                int result = removeGame(tokens[1]);
                
                if (result == 1)
                {
                    System.out.println(tokens[1] + " was successfully removed.");
                }
                else
                {
                    System.out.println("Unable to remove " + tokens[1]);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Something broke in Remove.");
            }
            
            return 1;
        }
        else if (tokens[0].equals("list"))
        {
            try
            {
                ResultSet rs = listGames();
                
                while (rs.next())
                {
                    String game = rs.getString("Title") + ", " + rs.getString("Genre") + ", " + rs.getString("Length");
                    
                    System.out.println(game);
                }
            }
            catch (Exception ex)
            {
                System.out.println("Something broke in List.");
            }
            
            return 1;
        }
        else if (tokens[0].equals("find"))
        {
            if (tokens.length != 3)
            {
                System.out.println("Incorrect number of arguments for Find.");
                return 1;
            }
            
            if (sanitize(tokens[1], tokens[2]))
            {
                try
                {
                    ResultSet rs = findGames(tokens[1], tokens[2]);
                    
                    System.out.println("Games Found:\n");

                    while (rs.next())
                    {
                        System.out.println(rs.getString("Title"));
                    }
                    
                }
                catch (Exception ex)
                {
                    System.out.println("Something broke in Find.");
                }
            }
            else
            {
                System.out.println("Incorrect genre or length.");
            }
            
            return 1;
        }
        else if (tokens[0].equals("search"))
        {
            if (tokens.length != 2)
            {
                System.out.println("Incorrect number of arguments for Search.");
                return 1;
            }
            
            try
            {
                if (searchGame(tokens[1]))
                {
                    System.out.println(tokens[1] + " was found in the database.");
                }
                else
                {
                    System.out.println(tokens[1] + " was not found in the database.");
                }
            }
            catch (Exception ex)
            {
                System.out.println("Something broke in Search.");
            }
            
            return 1;
        }
        else if (tokens[0].equals("help"))
        {
            System.out.println("Valid Commands:\n");
            System.out.println("Add,Title,Genre,Length to add a game.");
            System.out.println("Remove,Title to remove a game.");
            System.out.println("List to list all games.");
            System.out.println("Find,Genre,Length to find games with the specified genre and length.");
            System.out.println("Search,Title to see if a game is in the database.\n");
            System.out.println("Valid Genres:\n");
            System.out.println("Shooter, Multiplayer, RPG, Action, Fighting, OpenWorld, Other\n");
            System.out.println("Valid Lengths:\n");
            System.out.println("Multiplayer, Short, Medium, Long");
            
            return 1;
        }
        else if (tokens[0].equals("exit"))
        {
            return 0;
        }
        else
        {
            System.out.println("Command not found.");
            
            return 1;
        }
    }
    
    boolean sanitize(String inGenre, String inLength)
    {
        String[] genres = new String[]{"shooter", "multiplayer", "rpg", "action", "fighting", "openworld", "other"};
        String[] lengths = new String[]{"multiplayer", "short", "medium", "long"};
        
        boolean flagGenre = false;
        boolean flagLength = false;
        
        for (int i = 0; i < genres.length; i++)
        {
            if (inGenre.toLowerCase().trim().equals(genres[i]))
            {
                flagGenre = true;
            }
        }
        
        for (int i = 0; i < lengths.length; i++)
        {
            if (inLength.toLowerCase().trim().equals(lengths[i]))
            {
                flagLength = true;
            }
        }
        
        return flagGenre && flagLength;
    }
    
    int insertGame(String inTitle, String inGenre, String inLength) {
        
        int nRows = 0;
        try {
            Connection connection = getConnection();
            String sql = "insert or replace into backlog (Title, Genre, Length) values (?, ?, ?)";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, inTitle.trim());
            ps.setString(2, inGenre.trim());
            ps.setString(3, inLength.trim());
            nRows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nRows;
}
    
    ResultSet findGames(String inGenre, String inLength)
    {
        try {
            Connection connection = getConnection();
            String sql = "select Title from  backlog where Genre is ? and Length is ?";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, inGenre.trim());
            ps.setString(2, inLength.trim());
            ResultSet rs = ps.executeQuery();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    ResultSet listGames()
    {
         try {
            Connection connection = getConnection();
            String sql = "select * from  backlog";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //ResultSet rs = ps.getResultSet();
            
            
            while (rs.next())
                {
                    String game = rs.getString("Title") + ", " + rs.getString("Genre") + ", " + rs.getString("Length");
                    
                    System.out.println(game);
                }
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    int removeGame(String inTitle)
    {
     int nRows = 0;
        try {
            Connection connection = getConnection();
            String sql = "delete from backlog where Title is ?";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, inTitle);
            nRows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nRows;   
    }
    
    boolean searchGame(String inTitle)
    {
        try {
            Connection connection = getConnection();
            String sql = "select exists(select * from backlog where Title is ?)";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, inTitle);
            ResultSet rs = ps.executeQuery();
            
            int wasFound = rs.getInt(1);

            
            if (wasFound == 1)
                return true;
            else
                return false;                
        } 
        catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public void run()
    {
        reset();
        
        BufferedReader br = null;
        
//        try
//        {
//            String sql = "insert into backlog values ('Darkest Dungeon', 'RPG', 'Long')";
//
//            PreparedStatement ps
//                    = connection.prepareStatement(sql);
//            ps.executeUpdate();
//            
//            sql = "insert into backlog values ('Overwatch', 'Multiplayer', 'Multiplayer')";
//            
//            ps
//                    = connection.prepareStatement(sql);
//            ps.executeUpdate();
//            
//        }
//        catch (SQLException ex)
//        {
//            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        System.out.println("Good to go.");
        
        
        try
        {

            br = new BufferedReader(new InputStreamReader(System.in));
            int state = 0;

            while (true)
                {
                    System.out.print(">");
                    //read input
                    state = parse(br.readLine());
                    
                    if (state == 0)
                        break;
                }
        }
        catch (Exception ex)
        {
            System.out.println("Something broke somewhere.");
        }
        
    }
    

}
