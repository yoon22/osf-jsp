package com.osf.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ReadFile {
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	private static final String DRIVER;

	static {
		InputStream is = ReadFile.class.getResourceAsStream("/com/osf/test/config/db.properties");
		//d드라이브~~어쩌구 풀경로  써도 동작하지만 굳이 그럴필요없음! 
		Properties prop = new Properties();
		try {
			prop.load(is);

		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("url");
		USER = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
		DRIVER = prop.getProperty("classname");
	}
	private static Connection con = null;

	public static void main(String[] args) {
		System.out.println(URL);
		System.out.println(USER);
		System.out.println(PASSWORD);
		System.out.println(DRIVER);
	}
}