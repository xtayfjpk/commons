package com.nightsoul.commons;

import java.text.DecimalFormat;


public abstract class MiscUtils {
	private MiscUtils(){}
	
	public static String formatSize(long byteSize) {
		DecimalFormat format = new DecimalFormat("0.0");
		if(byteSize < 1024) {
			return "1KB";
		} else if(byteSize>=1024 && byteSize<1024*1024) {
			return format.format(byteSize * 1.0 / 1024) + "KB";
		} else if(byteSize>=1024*1024 && byteSize<1024*1024*1024) {
			return format.format(byteSize * 1.0 / 1024 / 1024) + "MB";
		} else {
			return format.format(byteSize * 1.0 / 1024 / 1024 / 1024) + "GB";
		}
	}
}
