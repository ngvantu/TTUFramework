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
public class SQLUpdateQuery<T> extends SQLQuery{
    
	public SQLUpdateQuery(Connection cnn, T obj){
		super(cnn, null);
		Mapper mapper = new SQLMapper(obj.getClass());
		try {
			String tableName = mapper.getTableName();
			String primaryKey = mapper.getPrimaryName();
			List<String> columnNames = mapper.getColumnNames();
	    	List<String> columnValues = mapper.getColumnValues(obj);
	    	String value = mapper.getPrimaryValue(obj);
	    	String setclause = "";
	    	for(int i = 0; i < columnNames.size(); i++)
	    		setclause += columnNames.get(i) + "=" + columnValues.get(i) + ",";
	    	setclause = setclause.substring(0, setclause.length()-1);
	    	query = String.format("UPDATE %s SET %s WHERE %s = '%s';",  tableName, setclause, primaryKey, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
