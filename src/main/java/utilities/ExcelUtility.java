package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static XSSFWorkbook workBook;
	static XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	static File file;
	static FileInputStream inputStream;

	public static <E> E returnCellData(XSSFCell currentCell) {

		E data = null;
		CellType typeOfCell = currentCell.getCellType();

		switch (typeOfCell) {

		case NUMERIC:
			return (E) Double.valueOf(currentCell.getNumericCellValue());

		case STRING:
			return (E) currentCell.getStringCellValue();

		case BOOLEAN:
			return (E) Boolean.valueOf(currentCell.getBooleanCellValue()).toString();

		}
		return data;

	}

	private static void setExcelFileContext(String filepath, String sheetName) throws IOException {
		file = new File(filepath);
		inputStream = new FileInputStream(file);

		workBook = new XSSFWorkbook(inputStream);
		sheet = workBook.getSheet(sheetName);
	}

	private static List<Object> setTestData() {
		List<Object> testData = new ArrayList<Object>();
		
		int totalTestDataRecords = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Map<String, String> dataRecord = new HashMap<String, String>();

		for (int i = 1; i <= totalTestDataRecords; i++) {
			int cellcount = sheet.getRow(i).getLastCellNum();
			for (int j = 0; j < cellcount; j++) {

				String key = returnCellData(sheet.getRow(0).getCell(j)).toString();
				String value = returnCellData(sheet.getRow(i).getCell(j)).toString();
				dataRecord.put(key, value);
			
			}
			
			System.out.println("Row" + i + " data is :" + dataRecord);
			Boolean result=testData.add(new HashMap<>(dataRecord));
	}
		System.out.println("TestData"+testData);
		return testData;
	}

	public static List<Object> createTestDataFromExecel(String filePath, String sheetName) throws IOException {
		setExcelFileContext(filePath, sheetName);
		List<Object> testData = setTestData();

		return testData;

	}

	
}