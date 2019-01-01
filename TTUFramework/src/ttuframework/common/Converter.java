package ttuframework.common;

import com.google.gson.Gson;

public class Converter {
	
	@SuppressWarnings("unchecked")
	public static <T> T ObjectToClass(Object obj, Class<?> cls) throws Exception{
		Gson gson = new Gson();
		return (T) gson.fromJson(gson.toJson(obj), cls);
	}
}
