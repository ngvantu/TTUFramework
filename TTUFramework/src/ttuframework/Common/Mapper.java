/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.Common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import static java.lang.reflect.Array.newInstance;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import ttuframework.Annotation.Column;
import ttuframework.Annotation.ForeignKey;
import ttuframework.Annotation.PrimaryKey;
import ttuframework.Annotation.Table;

/**
 *
 * @author Tu Nguyen
 */
public abstract class Mapper<T> {
    private Class<T> classOfT;
    protected abstract <T> void MapOneToMany(TTUConnection cnn, ResultSet rs, T obj);
    protected abstract <T> void MapToOne(TTUConnection cnn, ResultSet rs, T obj);

    // Map relattionship 
    public <T> T MapWithRelationship(TTUConnection cnn, ResultSet rs) throws InstantiationException, IllegalAccessException {
        T obj = (T) classOfT.newInstance();
        //Todo
        Annotation[] annotations = classOfT.getAnnotations();
        // Cần lấy các thuộc tính của 1 bảng T
        // VD: Bảng Student có ID, tên, điểm...
        
        /*
        Object columnMapping = FirstOrDefault(annotations, classOfT);
        if (columnMapping != null) {
            Column column = (Column) columnMapping;
               
        }
        */
        
        MapOneToMany(cnn, rs, obj);
        MapToOne(cnn, rs, obj);
        return obj;   
    }
    
    public <T> T MapWithOutRelationship(TTUConnection cnn, ResultSet rs) {
        //Todo
        return null;
    }
    
    public <T> String GetTableName() {
        Object[] tables = null;
        //Object[] tables = <T>.getClass().getAnnotations();
        // Todo
        Table table = (Table) FirstOrDefault(tables, Table.class);
        if (table != null) {
            return table.Name();
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
            if (column.Name().equals(name)) {
                return column;
            }
        }
        return null;
    }
    
    public Column FindColumn(String name, List<Column> listColumns) {
        for (Column column : listColumns) {
            if (column.Name().equals(name)) {
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