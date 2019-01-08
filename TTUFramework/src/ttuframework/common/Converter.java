package ttuframework.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Converter {
	
	@SuppressWarnings("unchecked")
	public static <T> T ObjectToClass(Object obj, Class<?> cls) throws Exception{
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		return (T) gson.fromJson(gson.toJson(obj), cls);
	}
}
