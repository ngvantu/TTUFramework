/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import ttuframework.annotation.ManyToOne;
import ttuframework.annotation.OneToMany;

/**
 *
 * @author Tu Nguyen
 * @param <T>
 */
public class SQLMapper extends Mapper{

	public SQLMapper(Class<?> cls) {
		super(cls);
	}

	@Override
	protected <T> HashMap<String, Object> mapManyToOne(Connection cnn, HashMap<String, Object> hmap, Class<?> cls) throws Exception {
		List<Field> fields = getAllFieldsManyToOne(cls);
		if (fields.size()==0)
			return hmap;
		for(Field field : fields){
			String tableName = field.getAnnotation(ManyToOne.class).tableName();
			String tableId = field.getAnnotation(ManyToOne.class).relationshipId();
			String value = (String) hmap.get(field.getName());
			String query = String.format("select * from %s where %s = '%s' ", tableName, tableId, value);
			ResultSet rs = cnn.createStatement().executeQuery(query);
			Mapper mapper = new SQLMapper(field.getType());
			List<Object> ls = mapper.mapWithoutRelationship(rs);
			hmap.put(field.getName(), ls.get(0));
			rs.close();
		}
		return hmap;
	}
	
	protected <T> HashMap<String, Object> mapOneToMany(Connection cnn, HashMap<String, Object> hmap, Class<?> cls) throws Exception {
		List<Field> fields = getAllFieldsOneToMany(cls);
		Field pKey = getPrimaryKey(cls);
		if (fields.size()==0)
			return hmap;
		for(Field field : fields){
			String tableName = field.getAnnotation(OneToMany.class).tableName();
			String tableId = field.getAnnotation(OneToMany.class).relationshipId();
			String value = (String) hmap.get(pKey.getName());
			String query = String.format("select * from %s where %s = '%s' ", tableName, tableId, value);
			ResultSet rs = cnn.createStatement().executeQuery(query);
			ParameterizedType genericType = (ParameterizedType) field.getGenericType();
			Class<?> type = (Class<?>) genericType.getActualTypeArguments()[0];
			Mapper mapper = new SQLMapper(type);
			List<Object> ls = mapper.mapWithRelationship(cnn, rs);
			hmap.put(field.getName(), ls);
			rs.close();
		}
		return hmap;
	}
}
