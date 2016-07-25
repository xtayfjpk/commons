package com.nightsoul.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public abstract class ZipUtils {
	private static final String ZIP_EXTENSION = ".zip";

	private ZipUtils(){}
	
	public static void zipDirectory(String srcDirectory) throws IOException {
		zipDirectory(new File(srcDirectory), null);
	}

	public static void zipDirectory(File srcDirectory) throws IOException {
		zipDirectory(srcDirectory, null);
	}

	public static void zipDirectory(String srcDirectory, String destZipFilepath) throws IOException {
		zipDirectory(new File(srcDirectory), new File(destZipFilepath));
	}

	public static void zipDirectory(File srcDirectory, File destZipFile) throws IOException {
		if(srcDirectory!=null && srcDirectory.exists()) {
			ZipOutputStream output = null;
			try {
				if(destZipFile==null) {
					destZipFile = getDefaultZipFile(srcDirectory);
				}
				output = new ZipOutputStream(destZipFile);
				zip(srcDirectory, output, StringUtils.EMPTY);
			} finally {
				IOUtils.closeQuietly(output);
			}
		}
		
	}
	
	private static void zip(File file, ZipOutputStream output, String prefixPath) throws IOException {
		String path = getEntryPath(file, prefixPath);
		if(file.isDirectory()) {
			// 以“/”结尾则为目录
			ZipEntry ze = new ZipEntry(path);
			output.putNextEntry(ze);
			output.closeEntry();
			for(File subFile : file.listFiles()) {
				zip(subFile, output, path);
			}
		} else if(file.isFile()) {
			ZipEntry ze = new ZipEntry(path);
			output.putNextEntry(ze);
			FileInputStream input = null;
			try {
				input = new FileInputStream(file);
				IOUtils.copy(input, output);
			} finally {
				IOUtils.closeQuietly(input);
			}
			output.closeEntry();
		}
	}

	private static String getEntryPath(File file, String prefixPath) {
		return file.isDirectory() ? prefixPath + file.getName() + "/" : prefixPath + file.getName();
	}

	public static void zipDirectoryFiles(String srcDirectory) throws IOException {
		zipDirectoryFiles(new File(srcDirectory));
	}

	public static void zipDirectoryFiles(File srcDirectory) throws IOException {
		zipDirectoryFiles(srcDirectory, null);
	}

	public static void zipDirectoryFiles(String srcDirectory, String destZipFilepath) throws IOException {
		zipDirectoryFiles(new File(srcDirectory), new File(destZipFilepath));
	}

	public static void zipDirectoryFiles(File srcDirectory, File destZipFile) throws IOException {
		if(srcDirectory!=null && srcDirectory.exists()) {
			if(srcDirectory.isFile()) {
				zipDirectory(srcDirectory, destZipFile);
			} else if(srcDirectory.isDirectory()) {
				ZipOutputStream output = null;
				try {
					if(destZipFile==null) {
						destZipFile = getDefaultZipFile(srcDirectory);
					}
					output = new ZipOutputStream(destZipFile);
					for(File file : srcDirectory.listFiles()) {
						zip(file, output, StringUtils.EMPTY);
					}
				} finally {
					IOUtils.closeQuietly(output);
				}
			}
		}
	}

	private static File getDefaultZipFile(File srcDirectory) {
		String name = FilenameUtils.getBaseName(srcDirectory.getName());
		return new File(srcDirectory.getParentFile(), name + ZIP_EXTENSION);
	}
	
	public static void zipFile(File file) throws IOException {
		zipFile(file, null);
	}

	public static void zipFile(String filepath) throws IOException {
		zipFile(new File(filepath));
	}

	public static void zipFile(File file, File destZipFile) throws IOException {
		zipDirectory(file, destZipFile);
	}

	public static void zipFile(String filepath, String destZipFilepath) throws IOException {
		zipFile(new File(filepath), new File(destZipFilepath));
	}

	public static void zipFiles(File[] files, File destZipFile) throws IOException {
		if(files!=null) {
			ZipOutputStream output = null;
			try {
				output = new ZipOutputStream(destZipFile);
				for(File file : files) {
					zip(file, output, StringUtils.EMPTY);
				}
			} finally {
				IOUtils.closeQuietly(output);
			}
		}
	}
	
	public void zipFiles(Collection<File> files, File destZipFile) throws IOException {
		zipFiles(files.toArray(new File[0]), destZipFile);
	}


	public static void unzip(File zipFile) throws IOException {
		unzip(zipFile, null);
	}

	public static void unzip(File zipFile, File destDirectory) throws IOException {
		if(zipFile!=null && zipFile.exists() || zipFile.isFile()) {
			if(destDirectory==null) {
				destDirectory = zipFile.getParentFile();
			}
			if(!destDirectory.exists()) {
				destDirectory.mkdirs();
			}
			ZipInputStream input = null;
			try {
				input = new ZipInputStream(new FileInputStream(zipFile));
				java.util.zip.ZipEntry zipEntry = null;
				while((zipEntry=input.getNextEntry()) != null) {
					boolean isDir = zipEntry.isDirectory();
					File entryFile = new File(destDirectory, zipEntry.getName());
					FileOutputStream output = null;
					try {
						if(isDir) {
							entryFile.mkdirs();
						} else {
							entryFile.getParentFile().mkdirs();
							output = new FileOutputStream(entryFile);
							IOUtils.copy(input, output);
						}
						input.closeEntry();
					} finally {
						IOUtils.closeQuietly(output);
					}
				}
				
			} finally {
				IOUtils.closeQuietly(input);
			}
		}
	}

	public static void unzip(String zipFilepath) throws IOException {
		unzip(new File(zipFilepath));
	}

	public static void unzip(String zipFilepath, String destDirectory) throws IOException {
		unzip(new File(zipFilepath), new File(destDirectory));
	}
	
	
}
