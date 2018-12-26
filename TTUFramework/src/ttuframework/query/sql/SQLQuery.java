/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ttuframework.Common.IQuery;

/**
 *
 * @author Tu Nguyen
 */
public class SQLQuery implements IQuery{
    
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
    public <T> List<T> ExecuteQuery() {
        List<T> res = new ArrayList();
        TTUSQLConnection cnn = new TTUSQLConnection(connectionString);
        SQLMapper mapper = new SQLMapper();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public <T> List<T> ExecuteQueryWithOutRelationship() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ExecuteNonQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
