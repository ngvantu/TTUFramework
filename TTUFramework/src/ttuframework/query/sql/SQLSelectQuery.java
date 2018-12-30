/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query.sql;

import java.sql.Connection;
import java.util.List;
import ttuframework.annotation.Column;
import ttuframework.mapper.SQLMapper;
import ttuframework.query.QueryGroupBy;
import ttuframework.query.QueryHaving;
import ttuframework.query.QueryRun;
import ttuframework.query.QueryWhere;

/**
 *
 * @author Tu Nguyen
 */
public class SQLSelectQuery<T> extends SQLQuery implements QueryWhere<T>, QueryGroupBy<T>, QueryHaving<T>, QueryRun<T>{
    
    private <T> SQLSelectQuery(Connection cnn, String connectionString, Class<?> cls) {
        super(cnn, connectionString);
        SQLMapper mapper = new SQLMapper(cls);
        List<Column> listColumns =mapper.getColumns();
        
        query = "SELECT";
        for (Column column : listColumns) {
            query = String.format("{0} {1},", query, column.name());
        }
        
        query = query.substring(0, query.length()-1);
        query = String.format("{0} from {1}", query, mapper.getTableName());
    }
    
    public static QueryWhere createInstance(Connection cnn, String connectionString, Class<?> cls) {
        return new SQLSelectQuery(cnn, connectionString, cls);
    }
    
    public QueryHaving allRow() {
        return this;
    }
    
    @Override
    public QueryHaving addWhere(String condition) {
        query = String.format("{0} WHERE {1}", query, condition);
        return this;
    }

    @Override
    public QueryRun addGroupBy(String columnNames) {
        query = String.format("{0} GROUP BY {1}", query, columnNames);
        return this;
    }

    @Override
    public QueryGroupBy<T> addHaving(String condition) {
        query = String.format("{0} HAVING {1}", query, condition);
        return this;
    }

    @Override
    public List<T> run() {
        return executeQuery();
    }
    
}
