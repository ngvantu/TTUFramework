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
import ttuframework.query.QueryGroupBy;
import ttuframework.query.QueryHaving;
import ttuframework.query.QueryRun;
import ttuframework.query.QueryWhere;

/**
 *
 * @author Tu Nguyen
 */
public class SQLSelectQuery<T> extends SQLQuery implements QueryWhere<T>, QueryGroupBy<T>, QueryHaving<T>, QueryRun<T>{
    
    private SQLSelectQuery(Connection cnn, Class<?> cls) {
        super(cnn, null, cls);
        Mapper mapper = new SQLMapper(cls);
        String tableName = mapper.getTableName();
        query = String.format("SELECT * FROM %s ", tableName);
    }
    
    public static <T> QueryWhere<T> createInstance(Connection cnn, Class<?> cls) {
        return new SQLSelectQuery<T>(cnn, cls);
    }
    
	@Override
    public QueryGroupBy<T> addWhere(String condition) {
        query = String.format("%s WHERE %s", query, condition);
        return this;
    }

	@Override
    public QueryHaving<T> addGroupBy(String columnNames) {
        query = String.format("%s GROUP BY %s", query, columnNames);
        return this;
    }

    @Override
    public QueryRun<T> addHaving(String condition) {
        query = String.format("%s HAVING %s", query, condition);
        return this;
    }

    @Override
    public List<T> run() {
        return executeQuery();
    }

	@Override
	public QueryGroupBy<T> allRows() {
		return this;
	}
}
