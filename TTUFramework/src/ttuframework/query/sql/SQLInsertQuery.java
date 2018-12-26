/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query.sql;

import java.sql.Connection;

/**
 *
 * @author Tu Nguyen
 */
public class SQLInsertQuery extends SQLQuery{
    
    public SQLInsertQuery(Connection cnn, String connectionString) {
        super(cnn, connectionString);
    }
    
}
