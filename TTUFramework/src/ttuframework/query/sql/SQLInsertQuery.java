/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query.sql;

import java.sql.Connection;
import java.util.List;

import ttuframework.mapper.Mapper;
import ttuframework.mapper.SQLMapper;

/**
 *
 * @author Tu Nguyen
 */
public class SQLInsertQuery<T> extends SQLQuery{
    
    public SQLInsertQuery(Connection cnn, T obj){
    	super(cnn, null);
    	Mapper mapper = new SQLMapper(obj.getClass());
    	String tableName = mapper.getTableName();
		try {
			List<String> columnNames = mapper.getColumnNames();
	    	List<String> columnValues = mapper.getColumnValues(obj);
			String subCoNames = columnNames.toString().substring(1, columnNames.toString().length()-1);
	    	String subCoValues = columnValues.toString().substring(1, columnValues.toString().length()-1);
	    	query = String.format("INSERT INTO %s(%s) VALUES (%s);",  tableName, subCoNames, subCoValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
