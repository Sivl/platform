package sivl.platform.common.utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map工具类
 */
@SuppressWarnings("rawtypes")
public class MapUtil {

	public static String getString(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null) {
			return null;
		}
		return value.toString();
	}
	
	public static Integer getInt(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null || "".equals(value.toString())) {
			return null;
		}
		return Integer.valueOf(value.toString());
	}
	
	public static Double getDouble(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null || "".equals(value.toString())) {
			return null;
		}
		return Double.valueOf(value.toString());
	}
	public static Double getDouble(Map map, String key, Double defValue) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null || "".equals(value.toString())) {
			return defValue;
		}
		return Double.valueOf(value.toString());
	}
	
	public static Long getLong(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null || "".equals(value.toString())) {
			return null;
		}
		return Long.valueOf(value.toString());
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getListString(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null) {
			return null;
		}
		return (List<String>) value;
	}

	@SuppressWarnings("unchecked")
	public static List<Map> getListMap(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null) {
			return null;
		}
		return (List<Map>) value;
	}
	
	public static Map getMap(Map map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null) {
			return null;
		}
		return (Map) value;
	}
	
	public static Date getDate(Map map, String key, String fmt) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null || "".equals(value.toString())) {
			return null;
		}
		return DateUtil.fmtStrToDate(value.toString(), fmt);
	}

	public static Float getFloat(Map<String, Object> map, String key) {
		if(map == null) {
			return null;
		}
		Object value = map.get(key);
		if(value == null || "".equals(value.toString())) {
			return null;
		}
		return Float.valueOf(value.toString());
	}
	
}