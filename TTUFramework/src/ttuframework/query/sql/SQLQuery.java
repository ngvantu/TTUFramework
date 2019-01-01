/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;
import ttuframework.query.Query;

/**
 *
 * @author Tu Nguyen
 */
public class SQLQuery implements Query{
    
    protected Connection connection;
    protected String query;
    private Class<?> cls;
    
    public SQLQuery(Connection cnn, String query, Class<?> cls) {
        this.query = query;
        this.connection = cnn;
        this.cls = cls;
    }
    
    public SQLQuery(Connection cnn, String query) {
        this.query = query;
        this.connection = cnn;
        this.cls = null;
    } 
    
    @Override
    public <T> List<T> executeQuery() {
    	try {
    		Statement stm = connection.createStatement();
    		ResultSet rs = stm.executeQuery(query);
    		Mapper mapper = new SQLMapper(cls);
    		List<T> listOfT = mapper.mapWithRelationship(connection, rs);
    		rs.close();
    		stm.close();
    		return listOfT;
    	}catch (Exception e) {
    		Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, e);
		}
    	return null;
    }

    @Override
    public <T> List<T> executeQueryWithOutRelationship() {
    	try {
    		Statement stm = connection.createStatement();
    		ResultSet rs = stm.executeQuery(query);
    		Mapper mapper = new SQLMapper(cls);
    		List<T> listOfT = mapper.mapWithoutRelationship(rs);
    		rs.close();
    		stm.close();
    		return listOfT;
    	}catch (Exception e) {
    		Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, e);
		}
    	return null;
    }

    @Override
    public int executeNonQuery() {
        try {
        	Statement stm = connection.createStatement();
        	stm.execute(query);
        	stm.close();
        	return 1;
        } catch (SQLException ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}