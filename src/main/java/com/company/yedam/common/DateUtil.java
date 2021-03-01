package com.company.yedam.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	
public static java.sql.Date toDate(String date) {
		Date result = null;
		//to do
		 SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd"); 	
			try {
		 	result = fm.parse(date);
			}catch(ParseException e) {
				e.printStackTrace();
			}
			return new java.sql.Date(result.getTime());
	}
}
	
	

