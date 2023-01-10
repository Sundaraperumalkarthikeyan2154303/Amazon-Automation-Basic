package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel{
	
	public static void main(String[] args) throws IOException {
		
		String fileLocation = "./LoginCredentials/LoginCredentials.xlsx";

		
		XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		
		for (int i = 0; i < 1; i++) {
			
			XSSFCell cell = row.getCell(0);
			String emailData = cell.getStringCellValue();
			System.out.println(emailData);
		}
		for (int j = 0; j < 1; j++) {
			
			XSSFCell cell = row.getCell(2);
			String passData = cell.getStringCellValue();
			System.out.println(passData);
		}

		
	}

}
