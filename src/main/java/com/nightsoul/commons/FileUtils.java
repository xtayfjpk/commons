package com.nightsoul.commons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public abstract class FileUtils {
	private FileUtils(){}
	
	/**
	 * 获取文件集合的绝对路径
	 * @param files
	 * @return
	 */
	public static List<String> retrieveFilepaths(File[] files) {
		List<String> paths = new ArrayList<String>();
		if(files!=null) {
			for(File file : files) {
				paths.add(file.getAbsolutePath());
			}
		}
		return paths;
	}
}
