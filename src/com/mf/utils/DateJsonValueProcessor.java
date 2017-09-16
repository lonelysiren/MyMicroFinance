package com.mf.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", new Date());
		
		list.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("dates", list);
		JsonConfig config = new JsonConfig();
		 config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor());           
		System.out.println(JSONObject.fromObject(map2, config));
	}
	
	private String format = "yyyy-MM-dd HH:mm:ss";  
	  
    public DateJsonValueProcessor()  
    {  
  
    }  
  
    public DateJsonValueProcessor(String format)  
    {  
  
        this.format = format;  
    }  
  
    public String getFormat()  
    {  
  
        return format;  
    }  
  
    public void setFormat(String format)  
    {  
  
        this.format = format;  
    }

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		 String[] obj = {};  
		 System.out.println("aDate");
	        if (value instanceof Date[])  
	        {  
	            SimpleDateFormat sf = new SimpleDateFormat(format);  
	            Date[] dates = (Date[]) value;  
	            obj = new String[dates.length];  
	            for (int i = 0; i < dates.length; i++)  
	            {  
	                obj[i] = sf.format(dates[i]);  
	            }  
	        }  
	        
	        return obj;  
	}

	@Override
	public Object processObjectValue(String arg0, Object value, JsonConfig arg2) {
		  System.out.println(value);
		if (value instanceof Date)  
        {  
            String str = new SimpleDateFormat(format).format((Date) value);  
            return str;  
        }  
		
        return value;  
	}  

}
