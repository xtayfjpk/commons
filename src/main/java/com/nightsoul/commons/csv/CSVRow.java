package com.nightsoul.commons.csv;


public class CSVRow {

	private final String[] cells;

	public CSVRow(int columns) {
		this.cells = new String[columns];
	}
	
	public void setCellValue(int index, String value){
		cells[index] = value;
	}
	
	public String getCellValue(int index){
		return cells[index];
	}
	
	public String[] getCells(){
		return cells;
	}
}
