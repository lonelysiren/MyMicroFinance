package com.mf.utils;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JavaWebToken {


	//The JWT signature algorithm we will be using to sign the token
	public String buildJwt(Date exp) {
	    String jwt = Jwts.builder()
	            .signWith(SignatureAlgorithm.HS256,"6666")//SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
	            .setExpiration(exp)//expTime是过期时间
	            .claim("key","vaule")//该方法是在JWT中加入值为vaule的key字段
	            .compact();
	    return jwt;
	}
	public static void main(String[] args) {
		JavaWebToken javaWebToken = new JavaWebToken();
	
	}
}
