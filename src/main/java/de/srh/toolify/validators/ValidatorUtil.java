package de.srh.toolify.validators;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidatorUtil {
	
	public static Object validate(Map<String, Object> map, Class<?> source){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
			  .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
		return mapper.convertValue(map,source);
	}
}