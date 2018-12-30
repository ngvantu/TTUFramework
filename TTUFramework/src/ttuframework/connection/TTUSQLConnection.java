/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ttuframework.query.QueryWhere;

/**
 *
 * @author Tu Nguyen
 */
public class TTUSQLConnection extends TTUConnection{
    
    Connection connection;
    public TTUSQLConnection(String connectionString) throws SQLException {
        this.connectionString = connectionString;
        this.connection = DriverManager.getConnection(connectionString);

    }
    
    public TTUSQLConnection(String connectionString, String username, String password) throws SQLException {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
        this.connection = DriverManager.getConnection(connectionString, username, password);
    }
    
    @Override
    public void open() {

    }

    @Override
    public void close() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TTUSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public <T> QueryWhere<T> select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int insert(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int update(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> int delete(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> executeQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> List<T> executeQueryWithOutRelationship(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int executeNonQuery(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
