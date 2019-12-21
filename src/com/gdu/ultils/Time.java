package com.gdu.ultils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

	public static String getDateTime()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}
}
