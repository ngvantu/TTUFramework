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
import ttuframework.query.sql.SQLDeleteQuery;
import ttuframework.query.sql.SQLInsertQuery;
import ttuframework.query.sql.SQLQuery;
import ttuframework.query.sql.SQLSelectQuery;
import ttuframework.query.sql.SQLUpdateQuery;

/**
 *
 * @author Tu Nguyen
 */
public class TTUSQLConnection extends TTUConnection{
    
    private Connection connection;
    
    public TTUSQLConnection(String connectionString){
        this.connectionString = connectionString;
        this.connection = null;
    }
    
    public TTUSQLConnection(String connectionString, String username, String password){
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
        this.connection = null;
    }
        
    @Override
    public void open() {
        try {
            if (connection == null || connection.isClosed()) {
                if (username == null)
                    this.connection = DriverManager.getConnection(connectionString);
                else this.connection = DriverManager.getConnection(connectionString, username, password);
            }         
        } catch (SQLException ex) {
            Logger.getLogger(TTUSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public <T> QueryWhere<T> select(Class<?> clazz) {
        return SQLSelectQuery.createInstance(this.connection, clazz);
    }

    @Override
    public <T> int insert(T obj) {
        SQLInsertQuery<T> query = new SQLInsertQuery<T>(connection, obj);
    	return query.executeNonQuery();
    }

    @Override
    public <T> int update(T obj) {
        SQLUpdateQuery<T> query = new SQLUpdateQuery<T>(connection, obj);
    	return query.executeNonQuery();
    }

    @Override
    public <T> int delete(T obj) {
        SQLDeleteQuery<T> query = new SQLDeleteQuery<T>(connection, obj);
        return query.executeNonQuery();
    }

    @Override
    public <T> List<T> executeQuery(Class<T> cls, String queryString) {
        SQLQuery query = new SQLQuery(connection, queryString, cls);
        return query.executeQuery();
    }

    @Override
    public <T> List<T> executeQueryWithOutRelationship(Class<T> cls, String queryString) {
        SQLQuery query = new SQLQuery(connection, queryString, cls);
        return query.executeQueryWithOutRelationship();
    }

    @Override
    public int executeNonQuery(String queryString) {
        SQLQuery query = new SQLQuery(connection, queryString);
        return query.executeNonQuery();
    }
}
