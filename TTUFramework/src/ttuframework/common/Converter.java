package ttuframework.common;

import java.util.Properties;

import com.google.gson.Gson;

public class Converter {
	
	@SuppressWarnings("unchecked")
	public static <T> T ObjectToClass(Object prop, Class<?> cls) throws Exception{
		Gson gson = new Gson();
		return (T) gson.fromJson(gson.toJson(prop), cls);
	}
}
