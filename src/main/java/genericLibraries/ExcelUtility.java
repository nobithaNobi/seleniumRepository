package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods to read data from excel
 * @author Srinivas Yadav
 */

public class ExcelUtility {
	private Workbook wb;
	DataFormatter df;
	
	/**
	 * This method initializes excel file
	 * @param excelPath
	 */
	public void excelInit(String excelPath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		df = new DataFormatter();
	}

	/**
	 * This method fetches single data from excel
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 */
	public String readFromExcel(String sheetName, int rowNum, int cellNum) {
		String data = df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		return data;
	}
	
	/**
	 * This method fetches multiple data from excel
	 * @param sheetName
	 * @param expectedTestName
	 * @return
	 */
	public Map<String, String> readFromExcel(String sheetName, String expectedTestName){
		Sheet sh = wb.getSheet(sheetName);
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i <= sh.getLastRowNum(); i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equals(expectedTestName)) {
				for(int j = i; j <= sh.getLastRowNum(); j++) {
					String key = df.formatCellValue(sh.getRow(j).getCell(2));
					String value = df.formatCellValue(sh.getRow(j).getCell(3));
					map.put(key, value);
					if(key.equals("####"))
						break;
				}
				break;
			}
		}
		return map;
	}
	
	/**
	 * This method is used to update test status to excel
	 * @param sheetName
	 * @param expectedTestName
	 * @param status
	 * @param excelPath
	 */
	public void updateTestStatus(String sheetName, String expectedTestName, String status, String excelPath) {
		Sheet sh = wb.getSheet(sheetName);
		
		for(int i = 0; i <= sh.getLastRowNum(); i++) {
			if(df.formatCellValue(sh.getRow(i).getCell(1)).equals(expectedTestName)) {
				Cell cell = sh.getRow(i).createCell(4);
				cell.setCellValue(status);
				break;
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to close excel
	 */
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
