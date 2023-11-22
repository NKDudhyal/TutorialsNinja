package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {

	public static String generatedEmailWithTimeStamp() {
		Date date = new Date();
		String newMail = date.toString().replace(" ", "_").replace(":", "_");
		return "ninja" + newMail + "@yopmail.com";
	}

	
	//DataFromExcel
	public static Object[][] getDataFromExcelFile(String sheetName) 
	{
		XSSFWorkbook workbook= null;
		File getFile = new File("D:\\Project 1\\TutorialNinja\\src\\main\\java\\testData\\TestDataExcelFile.xlsx");
		try {
			FileInputStream fileInput = new FileInputStream(getFile);
			workbook = new XSSFWorkbook(fileInput);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int colums = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][colums];

		for (int i = 0; i < rows; i++) 
		{
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < colums; j++) 
			{
				XSSFCell cells = row.getCell(j);
				CellType cellType = cells.getCellType();

				switch (cellType) {
				case STRING:
					data[i][j] = cells.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cells.getNumericCellValue());
					break;
				}
			}
		}
		return data;
	}
	
	
}
