/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import ttuframework.annotation.Column;
import ttuframework.annotation.ForeignKey;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.Converter;
import ttuframework.connection.TTUConnection;

/**
 *
 * @author Tu Nguyen
 */
public abstract class Mapper{
    private Class<?> classOfT;
    protected abstract <T> Properties mapOneToMany(TTUConnection cnn, ResultSet rs, T obj);
    protected abstract <T> Properties mapOneToOne(TTUConnection cnn, ResultSet rs, T obj);

    @SuppressWarnings("unchecked")
	public <T> T mapWithRelationship(TTUConnection cnn, ResultSet rs) throws Exception{
    	T obj = (T) classOfT.newInstance();
    	Field[] fields = classOfT.getDeclaredFields();
    	Properties prop = new Properties();
    	for (Field field : fields){
    		String attr = field.getName();
    		prop.setProperty(attr, rs.getString(attr));
    	}
        prop = mapOneToMany(cnn, rs, prop);
        prop = mapOneToOne(cnn, rs, prop);
        return obj;   
    }
    
    public Mapper(Class<?> cls){
    	classOfT = cls;
    }
    
    @SuppressWarnings("unchecked")
	public <T> T mapWithOutRelationship(ResultSet rs) throws Exception {
    	  T obj = (T) classOfT.newInstance();
    	  Field[] fields = classOfT.getDeclaredFields();
    	  Properties prop = new Properties();
    	  for (Field field : fields){
    		  String attr = field.getName();
    		  prop.setProperty(attr, rs.getString(attr));
    	  }
    	  System.out.println(prop);
    	  obj = Converter.propToClass(prop, classOfT);
    	  return obj;
    }
//    
//    public <T> String getTableName() {
//        Object[] tables = null;
//        //Object[] tables = <T>.getClass().getAnnotations();
//        // Todo
//        Table table = (Table) firstOrDefault(tables, Table.class);
//        if (table != null) {
//            return table.name();
//        }
//        return "";
//    }
//    
//    public <T> List<PrimaryKey> getPrimaryKeys() {
//        List<PrimaryKey> primaryKeys = new ArrayList<>();
//        // Todo
//        return primaryKeys;
//    }
//    
//    public <T> List<ForeignKey> getForeignKeys(String relationshipID) {
//        List<ForeignKey> foreignKeys = new ArrayList<>();
//        // Todo
//        return foreignKeys;
//    }
//    
//    public <T> List<Column> getColumns() {
//        List<Column> columns = new ArrayList<>();
//        // Todo
//        return columns;
//    }
//    
//    public <T> HashMap<Column, Object> getColumnValues(T obj) {
//        HashMap<Column, Object> listColumnValues = new HashMap<>();
//        // Todo
//        return listColumnValues;
//    }
//    
//    public Column findColumn(String name, HashMap<Column, Object> listColumns) {
//        for (Column column : listColumns.keySet()) {
//            if (column.name().equals(name)) {
//                return column;
//            }
//        }
//        return null;
//    }
//    
//    public Column findColumn(String name, List<Column> listColumns) {
//        for (Column column : listColumns) {
//            if (column.name().equals(name)) {
//                return column;
//            }
//        }
//        return null;
//    }
//    
//    protected Object firstOrDefault(Object[] annotation, Class c) {
//        for (Object a : annotation) {
//            if (a.getClass() == c) {
//                return a;
//            }
//        }
//        return null;
//    }
//    
//    protected List<Object> getAll(Object[] annotation, Class c) {
//        List<Object> listObj = new ArrayList();
//        for (Object a : annotation) {
//            if (a.getClass() == c) {
//                listObj.add(a);
//            }
//        }
//        return listObj;
//    }
//    
//    public Object getFirst(Iterable source) {
//        Iterator it = source.iterator();
//        if (it.hasNext()) {
//            return it.next();
//        }
//        return null;
//    }
}
