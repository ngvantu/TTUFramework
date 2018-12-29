package ttuframework.common;

import java.util.Properties;

import com.google.gson.Gson;

public class Converter {
	
	@SuppressWarnings("unchecked")
	public static <T> T propToClass(Properties prop, Class<?> cls){
		return (T) new Gson().fromJson(prop.toString(), cls);
	}
}
