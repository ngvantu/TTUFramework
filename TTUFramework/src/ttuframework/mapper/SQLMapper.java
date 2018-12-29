/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.mapper;

import java.sql.ResultSet;

import ttuframework.connection.TTUConnection;

/**
 *
 * @author Tu Nguyen
 * @param <T>
 */
public class SQLMapper extends Mapper{

	public SQLMapper(Class<?> cls) {
		super(cls);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected <T> void mapOneToMany(TTUConnection cnn, ResultSet rs, T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <T> void mapOneToOne(TTUConnection cnn, ResultSet rs, T obj) {
		// TODO Auto-generated method stub
		
	}

}
