package com.nightsoul.commons;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MD5Utils {
	private static class ThreadLocalMD5 extends ThreadLocal<MessageDigest> {
		public MessageDigest initialValue() {
			try {
				return MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
		}

		public MessageDigest getMD5() {
			MessageDigest md = super.get();
			md.reset();
			return md;
		}
	}

	private static ThreadLocalMD5 md5 = new ThreadLocalMD5();

	public static Mac getHmac(byte[] keyBytes, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
		Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm);
		Mac mac = Mac.getInstance(algorithm);
		mac.init(key);
		return mac;
	}

	public static Mac getHMACMD5(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		return getHmac(key, "HmacMD5");
	}

	public static Mac getHMACSHA1(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		return getHmac(key, "HmacSHA1");
	}


	public static byte[] md5(byte[] text) {
		MessageDigest md = md5.getMD5();
		md.update(text, 0, text.length);
		return md.digest();
	}
	
	public static String getMD5(byte[] text) {
		return getMD5(text, false);
	}

	public static String getMD5(byte[] text, boolean lowerCase) {
		return lowerCase ? HexUtils.byteArrayToHexString(md5(text)).toLowerCase() : HexUtils.byteArrayToHexString(md5(text));
	}

	public static String getMD5(String text) {
		return getMD5(text.getBytes());
	}

	public static String getMD5(String text, boolean lowerCase) {
		return getMD5(text.getBytes(), lowerCase);
	}

	public static byte[] md5(byte[] text1, byte[] text2) {
		MessageDigest md = md5.getMD5();
		md.update(text1, 0, text1.length);
		md.update(text2, 0, text2.length);
		return md.digest();
	}

	public static byte[] hmacMd5(byte[] text, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		return hmac_md5(text, 0, text.length, key);
	}

	public static byte[] hmac_md5(byte[] text, int toff, int tlen, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		int minKeyLen = 64;
		byte[] digest = new byte[16];

		if (key.length < minKeyLen) {
			byte[] t = new byte[minKeyLen];
			System.arraycopy(key, 0, t, 0, key.length);
			key = t;
		}

		Mac mac = getHMACMD5(key);
		mac.update(text, toff, tlen);
		System.arraycopy(mac.doFinal(), 0, digest, 0, 16);
		return digest;
	}
}
