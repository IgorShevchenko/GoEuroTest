package go.euro.parser;

import go.euro.data.Location;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * JSON parser for different return types. 
 */
public class JsonParser {
	
	/**
	 * JSON parser.
	 */
	private static Gson gson = new Gson(); 
	
	/**
	 * Parse given JSON to the specified type.
	 * @param json JSON to parse.
	 * @param formatType
	 * @return Object that can be casted to the specified type.
	 * @throws JsonSyntaxException
	 */
	public static Object parse(String json, Type formatType) throws JsonSyntaxException {	
		return gson.fromJson(json, formatType);	
	}	
	
	/**
	 * Parse given JSON to the list of locations.
	 * @param json JSON to parse.
	 * @return
	 * @throws JsonSyntaxException
	 */
	public static List<Location> parseLocations(String json) throws JsonSyntaxException {	
		Type type = new TypeToken<List<Location>>(){ }.getType();
       	return gson.fromJson(json, type);
	}

}
