package com.nightsoul.commons;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nightsoul.commons.util.CollectionUtils;
import com.nightsoul.commons.util.SQLUtils;

public class SQLUtilsTest {

	private static final String NAME_IN_ZHANGSAN_LISI = " name in ('zhangsan','lisi')";

	@Test
	public void testBuildInSQLStringListOfQextendsSerializable() {
		List<String> names = new ArrayList<String>();
		names.add("zhangsan");
		names.add("lisi");
		String inSql = SQLUtils.buildInSQL("name", names);
		assertEquals(inSql, NAME_IN_ZHANGSAN_LISI);
	}

	@Test
	public void testBuildInSQLStringSerializableArray() {
		String inSql = SQLUtils.buildInSQL("name", "zhangsan", "lisi");
		assertEquals(inSql, NAME_IN_ZHANGSAN_LISI);
	}

	@Test
	public void testSplitInSQLValues() {
		List<List<String>> lists = SQLUtils.splitInSQLValues(CollectionUtils.toStringList("zhangsan,lisi,wangwu"), 2);
		assertEquals(2, lists.size());
	}
}
