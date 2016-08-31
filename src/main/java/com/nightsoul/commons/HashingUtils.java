package com.nightsoul.commons;

import java.nio.charset.Charset;

import com.google.common.hash.Hashing;


public abstract class HashingUtils {
	private HashingUtils(){}
	
	public static String crc32(byte[] input) {
		return Hashing.crc32().hashBytes(input).toString();
	}

	public static String crc32(CharSequence value) {
		return crc32(value, Charset.defaultCharset());
	}

	public static String crc32(CharSequence value, Charset charset) {
		return Hashing.crc32().hashString(value, charset).toString();
	}

	public static String crc32c(CharSequence value) {
		return crc32c(value, Charset.defaultCharset());
	}
	
	public static String crc32c(CharSequence value, Charset charset) {
		return Hashing.crc32c().hashString(value, charset).toString();
	}

	public static String sha1(CharSequence value) {
		return crc32c(value, Charset.defaultCharset());
	}
	
	public static String sha1(CharSequence value, Charset charset) {
		return Hashing.sha1().hashString(value, charset).toString();
	}
	
	
	
}
