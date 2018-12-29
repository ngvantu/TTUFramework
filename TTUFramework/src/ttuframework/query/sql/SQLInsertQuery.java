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
public class SQLInsertQuery<T> extends SQLQuery{
    
    public SQLInsertQuery(Connection cnn, String connectionString, T obj) {
        super(cnn, connectionString);
        SQLMapper mapper = new SQLMapper(obj.getClass());
        String tableName = mapper.getTableName();
        List<PrimaryKey> primaryKeys = mapper.getPrimaryKeys();
        HashMap<Column, T> listColumnValues = mapper.getColumnValues(obj);
        
        if (!listColumnValues.isEmpty()) {
            String columnStr = "";
            String valueStr = "";
            
            for (Column column : listColumnValues.keySet()) {
                boolean isAutoID = false;
                for (PrimaryKey primaryKey : primaryKeys) {
                    if (column.name().equals(primaryKey.name()) && primaryKey.autoId()) {
                        isAutoID = true;
                        break;
                    }
                }
                
                if (!isAutoID){
                    String format = "{0}, ";
                    if (column.type() == DataType.NCHAR || column.type() == DataType.NVARCHAR)
                        format = "N'{0}', ";
                    else if (column.type() == DataType.CHAR || column.type() == DataType.VARCHAR)
                        format = "'{0}', ";
                    
                    columnStr += String.format("{0}, ", column.name());
                    valueStr += String.format(format, listColumnValues.get(column));
                }
            }
            
            if (!columnStr.isEmpty() && !valueStr.isEmpty()) {
                columnStr = columnStr.substring(0, columnStr.length()-2);
                valueStr = valueStr.substring(0, valueStr.length()-2);
                
                query = String.format("INSERT INTO {0} ({1} VALUES ({2})", tableName, columnStr, valueStr);
            }
        }
    }
    

    
}
