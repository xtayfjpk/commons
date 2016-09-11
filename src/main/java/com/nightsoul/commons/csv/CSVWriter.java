package com.nightsoul.commons.csv;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

import com.nightsoul.commons.util.ObjectUtils;
import com.nightsoul.commons.util.StringUtils;

public class CSVWriter implements Closeable{
	private final Writer writer;
	private final String delimiter;
	private final int columns;
	
	public CSVWriter(Writer writer, String delimiter, int columns) {
		this.writer = writer;
		this.delimiter = delimiter;
		this.columns = columns;
	}
	
	public CSVRow createRow(){
		return new CSVRow(columns);
	}
	
	public void writeRow(CSVRow row) throws IOException{
		for(String cell : row.getCells()){
			if(ObjectUtils.isNotNull(cell)){
				if(cell.contains(delimiter)){
					writer.write("\"");
					writer.write(cell);
					writer.write("\"");
				} else {
					writer.write(cell);
				}
			}
			writer.write(delimiter);
		}
		writer.write(StringUtils.NEWLINE);
	}
	
	public void close() throws IOException{
		writer.close();
	}
}
