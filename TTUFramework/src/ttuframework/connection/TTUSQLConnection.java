/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.connection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
        try {
            if (connection.isClosed()) {
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
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> QueryWhere<T> select(Class<?> clazz) {
        return SQLSelectQuery.createInstance(this.connection, this.connectionString, clazz);
    }

    @Override
    public <T> int insert(T obj) {
        SQLInsertQuery query = new SQLInsertQuery(this.connection, this.connectionString, obj);
    	return query.executeNonQuery();
    }

    @Override
    public <T> int update(T obj) {
        SQLUpdateQuery query = new SQLUpdateQuery(this.connection, this.connectionString, obj);
    	return query.executeNonQuery();
    }

    @Override
    public <T> int delete(T obj) {
        SQLDeleteQuery query = new SQLDeleteQuery(connection, connectionString, obj);
        return query.executeNonQuery();
    }

    @Override
    public <T> List<T> executeQuery(String queryString) {
        SQLQuery query = new SQLQuery(connection, connectionString, queryString);
        return query.executeQuery();
    }

    @Override
    public <T> List<T> executeQueryWithOutRelationship(String queryString) {
        SQLQuery query = new SQLQuery(connection, connectionString, queryString);
        return query.executeQueryWithOutRelationship();
    }

    @Override
    public int executeNonQuery(String queryString) {
        SQLQuery query = new SQLQuery(connection, connectionString, queryString);
        return query.executeNonQuery();
    }
    
}
