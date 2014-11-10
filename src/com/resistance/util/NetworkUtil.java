package com.resistance.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

public class NetworkUtil {

	private static Properties prop = new Properties();
	private static FileInputStream input;

	public static int getNewPort() {
		
		//Generate a unique port with VOODOO
		Calendar calendar = Calendar.getInstance();
		int port = (calendar.get(Calendar.MINUTE) * 100) + calendar.get(Calendar.SECOND) + 1024; 

		System.out.println(port);

		return port;

	}

	public static int getServerPort() {
		if(input == null) {
			try {
				input = new FileInputStream("config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(prop.getProperty("serverport"));
	}

	public static String getServerIP() {

		if(input == null) {
			try {
				input = new FileInputStream("config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop.getProperty("serverip");
	}

}