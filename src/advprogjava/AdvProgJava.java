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
                
            }
            
            //try
            //result = insertgame(tokens[1], tokens[2], tokens[3]
            //catch exception
            //Sysout "Incorrect # of arguments for add
            //
            //if result = 1
            // Sysout "Game successfully added."
            
            System.out.println("Add found");
            
            return 1;
        }
        else if (tokens[0].equals("remove"))
        {
            if (tokens.length != 2)
            {
                System.out.println("Incorrect number of arguments for Remove.");
                return 1;
            }
            //try
            //removegame(tokens[1])
            //catch exception
            //Sysout "Incorrect # of arguments for add
            //
            //if result = 1
            // Sysout "Game successfully removed."
            
            System.out.println("Remove found");
            
            return 1;
        }
        else if (tokens[0].equals("list"))
        {
            //try
            //resultSet rs = listGames
            //while rs.next
            //sysout rs.getString
            
            System.out.println("list found");
            
            return 1;
        }
        else if (tokens[0].equals("find"))
        {
            if (tokens.length != 3)
            {
                System.out.println("Incorrect number of arguments for Find.");
                return 1;
            }
            //Sanitize
            //rs = findGames(tokens[1], tokens[2])
            //while rs.next
            //sysout rs.getString
            
            System.out.println("find found");
            
            return 1;
        }
        else if (tokens[0].equals("search"))
        {
            if (tokens.length != 2)
            {
                System.out.println("Incorrect number of arguments for Search.");
                return 1;
            }
            //try
            //if searchgames(tokens[1])
            //sysout "Game is in the database
            //else
            //sysout "game is not in the database"
            
            System.out.println("search found");
            
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
            if (inGenre.toLowerCase().equals(genres[i]))
            {
                flagGenre = true;
            }
        }
        
        for (int i = 0; i < lengths.length; i++)
        {
            if (inLength.toLowerCase().equals(lengths[i]))
            {
                flagLength = true;
            }
        }
        
        return flagGenre && flagLength;
    }
    
    long insertGame(String inTitle, String inGenre, String inLength) {
        
        int nRows = 0;
        try {
            Connection connection = getConnection();
            String sql = "insert or replace into backlog (Title, Genre, Length) values (?, ?, ?)";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, inTitle);
            ps.setString(2, inGenre);
            ps.setString(3, inLength);
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
            ps.setString(1, inGenre);
            ps.setString(2, inLength);
            ResultSet rs = ps.getResultSet();
            return rs;
//            int counter = 1;
//            while (rs.next())
//            {
//                System.out.println(rs.getString(counter));
//                counter++;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    ResultSet listGames()
    {
         try {
            Connection connection = getConnection();
            String sql = "select Title from  backlog";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ResultSet rs = ps.getResultSet();
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
            String sql = "select Title from  backlog where Title is ?";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setString(1, inTitle);
            ResultSet rs = ps.getResultSet();
            return inTitle.equals(rs.getString(1));                 
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public void run()
    {
        getConnection();
        
        BufferedReader br = null;
        
        System.out.println("Good to go.");
        
        
        try
        {

            br = new BufferedReader(new InputStreamReader(System.in));
            int state = 0;

            while (true)
                {
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
