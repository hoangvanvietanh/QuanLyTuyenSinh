package com.gdu.ultils;

import java.io.File;

public class GetCurrentPath {

	public static String path()
	{
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		return path;
	}
}
