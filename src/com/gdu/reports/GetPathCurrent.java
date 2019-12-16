package com.gdu.reports;

import java.io.File;
import java.io.IOException;

public class GetPathCurrent {

	public static void main(String[] args) {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		System.out.println(path);

	}

}
