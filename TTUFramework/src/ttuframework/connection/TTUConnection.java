/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.connection;

import java.util.List;

import ttuframework.query.QueryWhere;

/**
 *
 * @author Tu Nguyen
 */
public abstract class TTUConnection {
	
    protected String connectionString;
    protected String username;
    protected String password;
    
    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
    	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public abstract void open();
    public abstract void close();
    public abstract <T> QueryWhere<T> select();
    public abstract <T> int insert(T obj);
    public abstract <T> int update(T obj);
    public abstract <T> int delete(T obj);
    public abstract <T> List<T> executeQuery(String query);
    public abstract <T> List<T> executeQueryWithOutRelationship(String query);
    public abstract int executeNonQuery(String query);
}
