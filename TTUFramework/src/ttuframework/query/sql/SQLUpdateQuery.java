/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query.sql;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import ttuframework.annotation.Column;
import ttuframework.annotation.PrimaryKey;
import ttuframework.common.DataType;
import ttuframework.mapper.SQLMapper;

/**
 *
 * @author Tu Nguyen
 */
public class SQLUpdateQuery<T> extends SQLQuery{
    
    public SQLUpdateQuery(Connection cnn, String connectionString, T obj) {
        super(cnn, connectionString);
        SQLMapper mapper = new SQLMapper(obj.getClass());
        String tableName = mapper.getTableName();
        List<PrimaryKey> primaryKeys = mapper.getPrimaryKeys();
        HashMap<Column, T> listColumnValues = mapper.getColumnValues(obj);
        
        if (listColumnValues != null) {
            String setStr = "";
            String whereStr = "";
            
            for (Column column : listColumnValues.keySet()) {
                String format = "{0} = {1}, ";
                if (column.type() == DataType.NCHAR || column.type() == DataType.NVARCHAR) {
                    format = "{0} = N'{1}', ";
                } else if (column.type() == DataType.CHAR || column.type() == DataType.VARCHAR) {
                    format = "{0} = '{1}', ";
                }
                
                setStr += String.format(format, column.name(), listColumnValues.get(column));
            }
            
            if (!setStr.isEmpty()) {
                setStr = setStr.substring(0, setStr.length()-2);
            }
            
            for (PrimaryKey primaryKey : primaryKeys) {
                Column column = mapper.findColumn(primaryKey.name(), listColumnValues);
                if (column != null) {
                    String format = "{0} = {1}, ";
                    if (column.type() == DataType.NCHAR || column.type() == DataType.NVARCHAR) {
                        format = "{0} = N'{1}', ";
                    } else if (column.type() == DataType.CHAR || column.type() == DataType.VARCHAR) {
                        format = "{0} = '{1}', ";
                    }
                
                    whereStr += String.format(format, column.name(), listColumnValues.get(column));
                }
            }
            
            if (!whereStr.isEmpty()) {
                whereStr = whereStr.substring(0, whereStr.length()-2);
                query = String.format("UPDATE {0} SET {1} WHERE {2}", tableName, setStr, whereStr);
            }
        }
    }
    
}
