package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.centipede.centipede.TestRunner;

public class ExcelUtils{
	private Logger log = LogManager.getLogger(TestRunner.class);
	public synchronized void publishReport(Object[][] data) {
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\";
		String filename = "Report";
		String sheetname = "regression";
		Sheet sheet = null;
		Row row;
		Cell cell;
		int rowNum=0;
		int cellNum;
		
		if(new File(filepath+filename.concat(".xlsx")).exists()) {
			try {
				File file = new File(filepath+filename.concat(".xlsx"));
				FileInputStream fip = new FileInputStream(file);
				Workbook workbook = WorkbookFactory.create(fip);
				int totalSheets = workbook.getNumberOfSheets();
				boolean isSheetExist = false;
				for(int i=0; i<totalSheets; i++) {
					if(workbook.getSheetAt(i).getSheetName().toString().equals(sheetname)) {
						sheet = workbook.getSheet(sheetname);
						rowNum = sheet.getLastRowNum()+1;
						isSheetExist = true;
						break;
					}
				}
				
				if(!isSheetExist) {
					sheet = workbook.createSheet(sheetname);
					rowNum = sheet.getLastRowNum();
				}
				
				for(int i=0; i<data.length; i++) {
					row = sheet.createRow(rowNum++);
					cellNum=0;
					for(int j=0; j<data[i].length; j++) {
						cell = row.createCell(cellNum++);
						String cellValue = String.valueOf(data[i][j]);
						try {
							cell.setCellValue(cellValue);
						}catch(IllegalArgumentException e) {
							cell.setCellValue("Content exceeds max cell length.... see data in logs");
							log.error(e.getMessage());
							log.info(cellValue);

						}
						if(cellValue == null || cellValue.isEmpty() || cellValue.isBlank()) {
							CellStyle style = workbook.createCellStyle();
							style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
							style.setFillPattern(FillPatternType.LEAST_DOTS);
							cell.setCellStyle(style);
						}else if(cellValue.equalsIgnoreCase("pass")) {
							CellStyle style = workbook.createCellStyle();
							style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
							style.setFillPattern(FillPatternType.DIAMONDS);
							cell.setCellStyle(style);
						}else if(cellValue.equalsIgnoreCase("fail")){
							CellStyle style = workbook.createCellStyle();
							style.setFillBackgroundColor(IndexedColors.RED1.getIndex());
							style.setFillPattern(FillPatternType.DIAMONDS);
							cell.setCellStyle(style);
						}
					}
				}
				fip.close();
				FileOutputStream fop = new FileOutputStream(file);
				workbook.write(fop);
				workbook.close();
				fop.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			XSSFWorkbook workbook = new XSSFWorkbook();
			sheet = workbook.createSheet(sheetname);
			rowNum = 0;
			try {
				for(int i =0; i<data.length; i++) {
					row = sheet.createRow(rowNum++);
					cellNum=0;
					for(int j=0; j<data[i].length; j++) {
						cell = row.createCell(cellNum++);
						cell.setCellValue(String.valueOf(data[i][j]));
					}
				}
				
				File file = new File(filepath+filename.concat(".xlsx"));
				FileOutputStream fop = new FileOutputStream(file);
				workbook.write(fop);
				workbook.close();
				fop.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
