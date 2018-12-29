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
    protected abstract <T> void mapOneToMany(TTUConnection cnn, ResultSet rs, T obj);
    protected abstract <T> void mapOneToOne(TTUConnection cnn, ResultSet rs, T obj);

    public <T> T MapWithRelationship(TTUConnection cnn, ResultSet rs) throws Exception{
        T obj = (T) classOfT.newInstance();
        // Cần lấy các thuộc tính của 1 bảng T
        // VD: Bảng Student có ID, tên, điểm...
        
        /*
        Object columnMapping = FirstOrDefault(annotations, classOfT);
        if (columnMapping != null) {
            Column column = (Column) columnMapping;
               
        }
        */
        
//        MapOneToMany(cnn, rs, obj);
//        MapToOne(cnn, rs, obj);
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
    	  obj = Converter.propToClass(prop, classOfT);
    	  return obj;
    }
    
    public <T> String GetTableName() {
        Object[] tables = null;
        //Object[] tables = <T>.getClass().getAnnotations();
        // Todo
        Table table = (Table) FirstOrDefault(tables, Table.class);
        if (table != null) {
            return table.name();
        }
        return "";
    }
    
    public <T> List<PrimaryKey> GetPrimaryKeys() {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        // Todo
        return primaryKeys;
    }
    
    public <T> List<ForeignKey> GetForeignKeys(String relationshipID) {
        List<ForeignKey> foreignKeys = new ArrayList<>();
        // Todo
        return foreignKeys;
    }
    
    public <T> List<Column> GetColumns() {
        List<Column> columns = new ArrayList<>();
        // Todo
        return columns;
    }
    
    public <T> HashMap<Column, Object> GetColumnValues(T obj) {
        HashMap<Column, Object> listColumnValues = new HashMap<>();
        // Todo
        return listColumnValues;
    }
    
    public Column FindColumn(String name, HashMap<Column, Object> listColumns) {
        for (Column column : listColumns.keySet()) {
            if (column.name().equals(name)) {
                return column;
            }
        }
        return null;
    }
    
    public Column FindColumn(String name, List<Column> listColumns) {
        for (Column column : listColumns) {
            if (column.name().equals(name)) {
                return column;
            }
        }
        return null;
    }
    
    protected Object FirstOrDefault(Object[] annotation, Class c) {
        for (Object a : annotation) {
            if (a.getClass() == c) {
                return a;
            }
        }
        return null;
    }
    
    protected List<Object> GetAll(Object[] annotation, Class c) {
        List<Object> listObj = new ArrayList();
        for (Object a : annotation) {
            if (a.getClass() == c) {
                listObj.add(a);
            }
        }
        return listObj;
    }
    
    public Object GetFirst(Iterable source) {
        Iterator it = source.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
