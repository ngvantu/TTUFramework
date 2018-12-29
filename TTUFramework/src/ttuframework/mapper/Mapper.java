/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.mapper;

import java.lang.annotation.Annotation;
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
    
    public Mapper(Class<?> cls){
    	classOfT = cls;
    }
    
    protected abstract <T> Properties mapOneToMany(TTUConnection cnn, ResultSet rs, T obj);
    protected abstract <T> Properties mapToOne(TTUConnection cnn, ResultSet rs, T obj);

    @SuppressWarnings("unchecked")
	public <T> T mapWithRelationship(TTUConnection cnn, ResultSet rs) throws Exception{
    	T obj = (T) classOfT.newInstance();
    	Field[] fields = classOfT.getDeclaredFields();
    	Properties prop = new Properties();
    	for (Field field : fields){
    		String attr = field.getName();
    		prop.setProperty(attr, rs.getString(attr));
    	}
        obj = Converter.propToClass(prop, classOfT);
        
        prop = mapOneToMany(cnn, rs, obj);
        prop = mapToOne(cnn, rs, obj);
        
        return obj;   
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
    
    public <T> String getTableName() {
        Field[] fields = classOfT.getDeclaredFields();

        Table table = firstOrDefault(fields, Table.class);
        if (table != null) {
            return table.name();
        }
        return "";
    }
    
    public <T> List<PrimaryKey> getPrimaryKeys() {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        Field[] fields = classOfT.getDeclaredFields();
        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {
                primaryKeys.add(primaryKey);
            }
        }
        return primaryKeys.size() > 0 ? primaryKeys : null;
    }
    
    public <T> List<ForeignKey> getForeignKeys(String relationshipID) {
        List<ForeignKey> foreignKeys = new ArrayList<>();
        Field[] fields = classOfT.getDeclaredFields();
        for (Field field : fields) {
            ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
            if (foreignKey != null) {
                foreignKeys.add(foreignKey);
            }
        }
        return foreignKeys.size() > 0 ? foreignKeys : null;
    }
    
    // get all column of a table annotation class
    public <T> List<Column> getColumns() {
        List<Column> columns = new ArrayList<>();
        Field[] fields = classOfT.getDeclaredFields();
        for (Field field : fields) {
            Column columnMapping = field.getAnnotation(Column.class);
            if (columnMapping != null) {
                columns.add(columnMapping);
            }
        }
        
        return columns.size() > 0 ? columns : null;
    }
    
    public <T> HashMap<Column, T> getColumnValues(T obj) {
        HashMap<Column, T> listColumnValues = new HashMap<>();
        Field[] fields = classOfT.getDeclaredFields();
        Properties prop = new Properties();

        for (Field field : fields) {
            Column columnMapping = field.getAnnotation(Column.class);
            if (columnMapping != null) {
                listColumnValues.put(columnMapping, obj);
            }
        }
        
        return listColumnValues.size() > 0 ? listColumnValues : null;
    }
    
    public <T> Column findColumn(String name, HashMap<Column, T> listColumns) {
        for (Column column : listColumns.keySet()) {
            if (column.name().equals(name)) {
                return column;
            }
        }
        return null;
    }
    
    public Column findColumn(String name, List<Column> listColumns) {
        for (Column column : listColumns) {
            if (column.name().equals(name)) {
                return column;
            }
        }
        return null;
    }
    
    // get value of annotaion class of the first field have similar annotation class
    protected <T> T firstOrDefault(Field[] fields, Class annotaionClass) {
        for (Field field : fields) {
            T annotation = (T)field.getAnnotation(annotaionClass);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }
    
    public <T> List<T> getAllAnnotation(Field[] fields, Class annotaionClass) {
        List<T> listObj = new ArrayList();
        for (Field field : fields) {
            // get annotation of a field if it is similar to this annotation class
            // return null if is dissimilar  
            T annotation = (T)field.getAnnotation(annotaionClass);
            if (annotation != null) {
                listObj.add(annotation);
            }
        }
        return listObj;
    }
    
    public Object getFirst(Iterable source) {
        Iterator it = source.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
