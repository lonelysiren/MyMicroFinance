package com.mf.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 System.out.println(sdf.format(new Date().getTime() +   30* 24 * 60 * 60 * 1000L));
	}
}
