/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

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

            statement.executeUpdate("drop table if exists points");
            statement.executeUpdate("create table points (XVal integer primary key, YVal integer)");
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
    long insertPoint(int xval, int yval) {
        
        int nRows = 0;
        try {
            Connection connection = getConnection();
            String sql = "insert or replace into points (XVal, YVal) values (?, ?)";

            PreparedStatement ps
                    = connection.prepareStatement(sql);
            ps.setInt(1, xval);
            ps.setInt(2, yval);
            nRows = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nRows;
}
    
    String getYVal(int xval) {
        try {
            Connection connection = getConnection();
            String sql = "select yval from points where xval=?";
            PreparedStatement ps
                    = connection.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            ps.setLong(1, xval);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return rs.getString("YVal");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvProgJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
    
    public void run()
    {
        reset();
        System.out.println(insertPoint(3, 7));
        System.out.println(insertPoint(4, 8));
        System.out.println(insertPoint(5, 9));
        
        System.out.println(getYVal(3));
        System.out.println(getYVal(4));
        System.out.println(getYVal(5));
    }
    
}
