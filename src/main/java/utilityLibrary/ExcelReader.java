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
import org.testng.annotations.Test;

import bsh.org.objectweb.asm.Type;

public class ExcelReader {

	String personInfoFile = "src/test/resources/PersonInfo_DataFile.xlsx";

	public HashMap<String, String> readTestData(int rowIndex) {

		HashMap<String, String> personInfo = new HashMap<>();
		try {
			File f = new File(personInfoFile);
			FileInputStream fis = new FileInputStream(f);

			XSSFWorkbook book = new XSSFWorkbook(fis);

			XSSFSheet userInfo = book.getSheet("userName");

			XSSFRow firstRow = userInfo.getRow(0);
			int index = 0;
			for (Cell cell : firstRow) {
				String value = userInfo.getRow(rowIndex).getCell(index).getStringCellValue();

				personInfo.put(cell.getStringCellValue(), value);
				index++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return personInfo;
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
