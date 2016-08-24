package com.atguigu.crm.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

public class PropertyFilter {
	

	
	public static Map<String, Object> parsePropertyFiltersToMybatisParams(List<PropertyFilter> filters){
		Map<String, Object> params = new HashMap<>();
		
		for(PropertyFilter filter: filters){
			String propertyName = filter.getPropertyName();
			Object propertyVal = filter.getPropertyVal();
			Class propertyType = filter.getPropertyType();
			MatchType matchType = filter.getMatchType();
			//转为目标类型. 包括基本数据类型和 Date 类型
			propertyVal = ConvertUtils.convert(propertyVal, propertyType);
			//若比较方式为 Date 则把值转为 Date 类型
//			if(matchType == MatchType.LIKE){
//				propertyVal = "%" + propertyVal + "%";
//			}
			
			params.put(propertyName, propertyVal);
		}
		
		return params;
	}
	
	public static List<PropertyFilter> parseRequestParamsToPropertyFilters(Map<String, Object> params){
		if(params != null && params.size() > 0){
			List<PropertyFilter> filters = new ArrayList<>();
			
			for(Map.Entry<String, Object> entry: params.entrySet()){
				String paramName = entry.getKey();
				Object paramVal= entry.getValue();
				
				if(paramVal == null || paramVal.toString().trim().equals("")){
					continue;
				}
				
				PropertyFilter filter = new PropertyFilter(paramName, paramVal);
				filters.add(filter);
			}
			
			return filters;
		}
		
		return null;
	}
	
	//parameterName:LIEKS_contact. 实际上就是在浏览器端去除前缀后的参数名
	//parameterVal:aaa. 参数值.
	public PropertyFilter(String parameterName, Object parameterVal) {
		String str = StringUtils.substringBefore(parameterName, "_"); //LIEKS
		String matchTypeCode = StringUtils.substring(str, 0, str.length() - 1); //LIKE
		String propertyTypeCode = StringUtils.substring(str, str.length() - 1); //S
		
		String propertyName = StringUtils.substringAfter(parameterName, "_"); //contact
		
		this.propertyVal = parameterVal;
		this.propertyName = propertyName;
		this.matchType = Enum.valueOf(MatchType.class, matchTypeCode); //可以把字符串转为对应的枚举类对象
		this.propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getType();
	}
	
	private String propertyName;
	private Object propertyVal;
	
	private MatchType matchType;
	private Class propertyType;
	
	public String getPropertyName() {
		return propertyName;
	}

	public Object getPropertyVal() {
		return propertyVal;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public Class getPropertyType() {
		return propertyType;
	}

	public enum MatchType{
		LIKE, EQ, GT, GE, LT, LE;
	}
	
	public enum PropertyType{
		I(Integer.class), L(Long.class), F(Float.class), D(Date.class), S(String.class), O(Object.class);
		
		private Class type;
		
		private PropertyType(Class type) {
			this.type = type;
		}
		
		public Class getType() {
			return type;
		}
	}
	
}
