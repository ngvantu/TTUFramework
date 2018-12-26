/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ttuframework.Common.ICanAddWhere;
import ttuframework.Common.TTUConnection;

/**
 *
 * @author Tu Nguyen
 */
public class TTUSQLConnection extends TTUConnection{
    
    Connection connection;
    public TTUSQLConnection(String connectionString) {
        try {
            this.connectionString = connectionString;
            this.connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(TTUSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    @Override
    public void Open() {
        
    }

    @Override
    public void Close() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TTUSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public <T> ICanAddWhere<T> Select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int Insert(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int Update(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int Delete(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> ExecuteQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> ExecuteQueryWithOutRelationship(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ExecuteNonQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
