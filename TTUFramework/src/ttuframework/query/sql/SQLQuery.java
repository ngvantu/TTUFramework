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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ttuframework.Student;

import ttuframework.connection.TTUSQLConnection;
import ttuframework.mapper.SQLMapper;
import ttuframework.query.Query;

/**
 *
 * @author Tu Nguyen
 */
public class SQLQuery implements Query{
    
    protected String connectionString;
    protected Connection connection;
    protected String query;
    
    public SQLQuery(Connection cnn, String connectionString) {
        this.connectionString = connectionString;
        this.connection = cnn;
    }    
       
    public SQLQuery(Connection cnn, String connectionString, String query) {
        this.connectionString = connectionString;
        this.connection = cnn;
        this.query = query;
    }
    
    @Override
    public <T> List<T> executeQuery() {
        List<T> res = new ArrayList<>();
        TTUSQLConnection cnn = null;
        try {
            cnn = new TTUSQLConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set static data, change after
        SQLMapper mapper = new SQLMapper(Student.class);
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                res.add(mapper.mapWithRelationship(cnn, rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public <T> List<T> executeQueryWithOutRelationship() {
        List<T> res = new ArrayList<>();
        
        // set static data, change after
        SQLMapper mapper = new SQLMapper(Student.class);
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                res.add(mapper.mapWithOutRelationship(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public int executeNonQuery() {
        TTUSQLConnection cnn = null;
        try {
            cnn = new TTUSQLConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnn.executeNonQuery(query);
    }
    
}
