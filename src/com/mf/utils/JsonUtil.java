package com.mf.utils;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class JsonUtil {
	public String readJSONData(HttpServletRequest request) {
		  StringBuffer json=new StringBuffer();
	        String lineString=null;
	        try {
	            BufferedReader reader=request.getReader();
	            while ((lineString=reader.readLine())!=null) {
	                json.append(lineString);                
	            }
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	        
	    
		return json.toString();
		
	}

	
}
