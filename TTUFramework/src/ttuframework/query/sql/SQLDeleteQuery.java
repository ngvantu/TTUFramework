/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query.sql;

import java.sql.Connection;

import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;

/**
 *
 * @author Tu Nguyen
 */
public class SQLDeleteQuery<T> extends SQLQuery{
    
    public SQLDeleteQuery(Connection cnn, T obj) {
    	super(cnn, null);
    	Mapper mapper = new SQLMapper(obj.getClass());
    	String tableName = mapper.getTableName();
    	String primaryKey = mapper.getPrimaryName();
    	String value = mapper.getPrimaryValue(obj);
    	query = String.format("DELETE FROM %s WHERE %s = '%s';",  tableName, primaryKey, value);
    }
}
