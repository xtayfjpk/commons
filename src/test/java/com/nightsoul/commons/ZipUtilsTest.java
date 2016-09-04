package com.nightsoul.commons;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.nightsoul.commons.util.ZipUtils;

public class ZipUtilsTest {

	@Test
	public void testZipDirectoryString() {
		fail("Not yet implemented");
	}

	@Test
	public void testZipDirectoryFile() throws IOException {
		ZipUtils.zipDirectory(new File("D:\\work\\myprojects\\test"));
	}

	@Test
	public void testZipDirectoryStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testZipDirectoryFileFile() throws Exception {
		ZipUtils.zipDirectory(new File("D:\\work\\myprojects\\commons"), new File("..\\commons.zip"));
	}

	@Test
	public void testZipDirectoryFilesString() {
		fail("Not yet implemented");
	}

	@Test
	public void testZipDirectoryFilesFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testZipDirectoryFilesStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testZipDirectoryFilesFileFile() throws IOException {
		ZipUtils.zipDirectoryFiles(new File("D:\\aaalicense\\20160714001.lic"));
	}

	@Test
	public void testUnzipFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnzipFileFile() throws IOException {
		ZipUtils.unzip(new File("D:\\work\\myprojects\\gradle-test.zip"), new File("D:\\gradle-test"));
	}

	@Test
	public void testUnzipString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnzipStringString() {
		fail("Not yet implemented");
	}

}
