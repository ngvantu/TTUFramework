/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.mapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ttuframework.annotation.Column;
import ttuframework.annotation.ManyToOne;
import ttuframework.annotation.OneToMany;
import ttuframework.annotation.PrimaryKey;
import ttuframework.annotation.Table;
import ttuframework.common.Converter;

/**
 *
 * @author Tu Nguyen
 */
public abstract class Mapper{
    
	private Class<?> classOfT;
    
    public Mapper(Class<?> cls){
    	classOfT = cls;
    }
    
    protected abstract <T> HashMap<String, Object> mapManyToOne(Connection cnn, HashMap<String, Object> hmap, 
    														Class<?> cls) throws Exception;
    protected abstract <T> HashMap<String, Object> mapOneToMany(Connection cnn, HashMap<String, Object> hmap, 
															Class<?> cls) throws Exception;    
	
    public <T> List<T> mapWithoutRelationship(ResultSet rs) throws Exception{
		List<T> listOfT = new ArrayList<T>();
		Field[] fields = classOfT.getDeclaredFields();
		HashMap<String, Object> hmap = new HashMap<>();
		while(rs.next()){
			for (Field field : fields){
				String attr = field.getName();
				Column column = (Column) field.getAnnotation(Column.class);
				if ( column != null)
					hmap.put(attr, rs.getString(attr));
			}
			listOfT.add(Converter.ObjectToClass(hmap, classOfT));
		}
		return listOfT;
	}
	
	public <T> List<T> mapWithRelationship(Connection cnn, ResultSet rs) throws Exception{
		List<T> listOfT = new ArrayList<T>();
		Field[] fields = classOfT.getDeclaredFields();
		HashMap<String, Object> hmap = new HashMap<>();
		while(rs.next()){
			for (Field field : fields){
				String attr = field.getName();
				Column column = (Column) field.getAnnotation(Column.class);
				if ( column != null)
					hmap.put(attr, rs.getString(attr));
			}
			hmap = mapManyToOne(cnn, hmap, classOfT);
			hmap = mapOneToMany(cnn, hmap, classOfT);
			listOfT.add(Converter.ObjectToClass(hmap, classOfT));
		}
		return listOfT;
	}
  
	protected <T> List<Field> getAllFieldsManyToOne(Class<?> cls){
		Field[] fields = cls.getDeclaredFields();
		List<Field> lfs = new ArrayList<>();
		for (Field field : fields)
			if (field.getAnnotation(ManyToOne.class)!= null)
				lfs.add(field);
		return lfs;
	}
	
	protected <T> List<Field> getAllFieldsOneToMany(Class<?> cls){
		Field[] fields = cls.getDeclaredFields();
		List<Field> lfs = new ArrayList<>();
		for (Field field : fields)
			if (field.getAnnotation(OneToMany.class)!= null)
				lfs.add(field);
		return lfs;
	}
	
	protected Field getPrimaryKey(Class<?> cls){
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields)
			if (field.getAnnotation(PrimaryKey.class)!= null)
				return field;
		return null;
	}
	
	public String getTableName(){
		return classOfT.getAnnotation(Table.class).name();
	}
	
	public String getPrimaryName(){
		return getPrimaryKey(classOfT).getName();
	}
	
	public <T> String getPrimaryValue(T obj) {
		Map<String, Object> map = getDataClass(obj);
		return map.get(getPrimaryName()).toString();
	}

	public List<String> getColumnNames() {
		List<String> result = new ArrayList<>();
 		Field[] fields = classOfT.getDeclaredFields();
		for (Field field : fields){
			Column column = (Column) field.getAnnotation(Column.class);
			if ( column != null)
				result.add(field.getName());
		}
		return result;
	}

	public <T> List<String> getColumnValues(T obj) throws Exception {
		Map<String, Object> map = getDataClass(obj);
		List<String> result = new ArrayList<>();
		List<String> columns = getColumnNames();
		for (String string : columns) {
			result.add("'" + map.get(string).toString()+ "'");
		}
		return result;
	}
	
	public <T> Map<String, Object> getDataClass(T obj){
		try{
			Map<String, Object> result = new HashMap<String, Object>(); 
			 BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			 for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		        Method reader = pd.getReadMethod();
		        if (reader != null)
		            result.put(pd.getName(), reader.invoke(obj));
		    }
		    return result;
		}
		catch (Exception e) {
		}
		return null;
	}
}
