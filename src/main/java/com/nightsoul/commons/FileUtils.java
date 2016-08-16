package com.nightsoul.commons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;


public abstract class FileUtils {
	private FileUtils(){}
	
	private static final Function<Integer, String> DEFAULT_BACKUP_DIR_SUFFIX = new Function<Integer, String>() {
		@Override
		public String apply(Integer index) {
			return index==0 ? "_bak" : ("_bak" + index);
		}
	};
	
	/**
	 * 获取文件集合的绝对路径
	 * @param files
	 * @return
	 */
	public static List<String> retrieveFilepaths(File[] files) {
		List<String> paths = new ArrayList<String>();
		if(ObjectUtils.isNull(files)) {
			for(File file : files) {
				paths.add(file.getAbsolutePath());
			}
		}
		return paths;
	}
	
	
	/**
	 * 定位备份目录，该备份目录与源目录平级
	 * @param originalPath 源目录路径
	 * @return
	 */
	public static File locateBackupDir(String originalPath) {
		return locateBackupDir(originalPath, 0, null);
	}

	/**
	 * 定位备份目录，该备份目录与源目录平级
	 * @param originalPath 源目录路径
	 * @param suffixFunction 备份目录后缀生成函数
	 * @return
	 */
	public static File locateBackupDir(String originalPath, Function<Integer, String> suffixFunction) {
		return locateBackupDir(originalPath, 0, suffixFunction);
	}


	private static File locateBackupDir(String originalPath, int index, Function<Integer, String> suffixFunction) {
		File originalDir = new File(originalPath);
		Preconditions.checkArgument(originalDir.isDirectory(), String.format("%s is not a directory", originalPath));
		suffixFunction = ObjectUtils.isNull(suffixFunction) ? DEFAULT_BACKUP_DIR_SUFFIX : suffixFunction;
		String suffix = suffixFunction.apply(index);
		File backupDir = new File(originalDir.getParentFile(), FilenameUtils.getName(originalPath + suffix));
		return backupDir.exists() ? locateBackupDir(originalPath, index + 1, suffixFunction) : backupDir;
	}
}
