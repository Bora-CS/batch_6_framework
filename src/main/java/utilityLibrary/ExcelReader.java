package utilityLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	String personInfoFile = "src/test/resources/PersonInfo_DataFile.xlsx";

	public HashMap<String, String> readTestData(String sheetName, int rowIndex) {

		HashMap<String, String> personInfo = new HashMap<>();
		try {
			File f = new File(personInfoFile);
			FileInputStream fis = new FileInputStream(f);

			XSSFWorkbook book = new XSSFWorkbook(fis);

			XSSFSheet userInfo = book.getSheet(sheetName); //"userName"

			XSSFRow firstRow = userInfo.getRow(0);
			int index = 0;
			for (Cell cell : firstRow) {
				String value = userInfo.getRow(rowIndex).getCell(index).getStringCellValue();

				personInfo.put(cell.getStringCellValue(), value);
				index++;
			}

			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return personInfo;
	}
	

	public HashMap<String, String> readTestData(String sheetName, String key) {

		HashMap<String, String> dataMap = new HashMap<>();
		try {
			File f = new File(personInfoFile);
			FileInputStream fis = new FileInputStream(f);

			XSSFWorkbook book = new XSSFWorkbook(fis);

			XSSFSheet Sheet = book.getSheet(sheetName);
			
			int rowIndex=-1;
			for (int i = 0; i <= Sheet.getLastRowNum(); i++) {

				String currentkey = Sheet.getRow(i).getCell(0).getStringCellValue();
				if(currentkey.equalsIgnoreCase(key)) {
					rowIndex = i;
					break;
				}
			}

			int index = 0;
			for (Cell cell : Sheet.getRow(0)) {
				String value = Sheet.getRow(rowIndex).getCell(index).getStringCellValue();

				dataMap.put(cell.getStringCellValue(), value);
				index++;
			}

			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataMap;
	}

	public void readExcel() {
		try {
			File excel = new File(personInfoFile);
			FileInputStream f = new FileInputStream(excel);

			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet dataSheet = workbook.getSheet("userName");

			XSSFRow row = dataSheet.getRow(0);

			XSSFCell cell = row.getCell(3);

			System.out.println("this is cell: " + cell);

			for (int rowIndex = 0; rowIndex <= dataSheet.getLastRowNum(); rowIndex++) {

				XSSFCell cell2 = dataSheet.getRow(rowIndex).getCell(5);
				CellType tpye = cell2.getCellType();

				if (tpye == CellType.NUMERIC) {
					System.out.println("this is number from the loop: " + cell2.getNumericCellValue()
							+ "  the cell type is: " + tpye);
				} else {
					System.out.println("this is cell from the loop: " + cell2 + "  the cell type is: " + tpye);
				}
			}

			for (int rowIndex = 0; rowIndex <= dataSheet.getLastRowNum(); rowIndex++) {

				XSSFRow row2 = dataSheet.getRow(rowIndex);

				int lastColumIndex = row2.getLastCellNum();

				for (int columIndex = 0; columIndex < lastColumIndex; columIndex++) {
					XSSFCell tempCell = row2.getCell(columIndex);
					System.out.print(" " + tempCell + " ");
				}
				System.out.println();

			}

			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
