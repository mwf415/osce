package com.youyicn;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;


public class JunitTest {

	@Test
	public void test(){
		DateTime dt = new DateTime();
		Date date = dt.toDate();
		dt.withYear(2018);
		dt.withMonthOfYear(5);
		System.out.println(dt.dayOfMonth().withMaximumValue().secondOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));
		System.out.println(dt.dayOfMonth().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss"));
		
		String userNames = "aaaa(张三)";
		int index = userNames.indexOf("(");
		String loginName = userNames.substring(0, index);
		String realName = userNames.substring(index+1, userNames.length()-1);
		System.out.println("111");
	}
	
}
